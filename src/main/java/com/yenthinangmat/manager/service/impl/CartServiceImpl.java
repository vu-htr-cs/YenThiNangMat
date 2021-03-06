package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.*;
import com.yenthinangmat.manager.service.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
    final
    InventoryService inventoryService;
    final
    ComboService comboService;
    final
    ProductService productService;
    final
    DBillService dBillService;
    Map<Long,CartItem> listProduct=new HashMap<>();
    Map<Long,CartItemCombo> listCombo=new HashMap<>();

    public CartServiceImpl(InventoryService inventoryService, ComboService comboService, ProductService productService, DBillService dBillService) {
        this.inventoryService = inventoryService;
        this.comboService = comboService;
        this.productService = productService;
        this.dBillService = dBillService;
    }

    @Override
    public void clear() {
        listCombo.clear();
        listProduct.clear();
    }

    @Override
    public void removeProduct(Long id) {
        listProduct.remove(id);
    }

    @Override
    public void removeCombo(Long id) {
        listCombo.remove(id);
    }

    @Override
    public void addProduct(CartItem cartItem) {
        CartItem product=listProduct.get(cartItem.getProductId());
        if(product==null){
            listProduct.put(cartItem.getProductId(),cartItem);

        }
        else product.setQty(product.getQty()+1);
    }

    @Override
    public void addCombo(CartItemCombo cartItemCombo) {
        CartItemCombo combo=listCombo.get(cartItemCombo.getComboId());
        if(combo==null){
            listCombo.put(cartItemCombo.getComboId(),cartItemCombo);
        }
        else combo.setQty(combo.getQty()+1);
    }

    @Override
    public Collection<CartItem> getAllProduct() {
        return listProduct.values();
    }

    @Override
    public Collection<CartItemCombo> getAllCombo() {
        return listCombo.values();
    }

    @Override
    public void updateProductQty(Long id, int qty) {
       listProduct.get(id).setQty(qty);
    }

    @Override
    public void updateComboQty(Long id, int qty) {
        listCombo.get(id).setQty(qty);
    }

    @Override
    public void updatePD(Long id, byte discount) {
        listProduct.get(id).setDiscount(discount);
    }

    @Override
    public void updateCD(Long id, byte discount) {
        listCombo.get(id).setDiscount(discount);
    }

    @Override
    @Transactional
    public void saveInvoice() {
        Collection<CartItem> mylistP=listProduct.values();
        Collection<CartItemCombo> mylistC=listCombo.values();
        Map<Long,ProductQty> mymap=new HashMap<>();
        mylistP.forEach(item->{
            ProductQty productQty=mymap.get(item.getProductId());
            if(productQty==null){
                ProductQty productQtyNew=new ProductQty();
                productQtyNew.setProductId(item.getProductId());
                productQtyNew.setQty(item.getQty());
                mymap.put(productQtyNew.getProductId(),productQtyNew);
            }
            else{
                productQty.setQty(productQty.getQty()+item.getQty());
            }
        });
        mylistC.forEach(item -> { //item la 1 combo
            List<ComboProductEntity> list=comboService.findOneE(item.getComboId()).getCplist();
            list.forEach(comboproduct->{ //combo product la trong combo do co product nao- soluong bao nhieu
                ProductQty productQty=mymap.get(comboproduct.getProduct().getId());
                if(productQty==null){
                    ProductQty productQtyNew=new ProductQty();
                    productQtyNew.setProductId(comboproduct.getProduct().getId());
                    productQtyNew.setQty(comboproduct.getQty()* item.getQty());
                    mymap.put(productQtyNew.getProductId(),productQtyNew);
                }
                else{
                    productQty.setQty(productQty.getQty() + item.getQty()* comboproduct.getQty());
                }

            });
        });
        // da co 1 list sp va soluong can phai update
        mymap.forEach((id,productqty)-> inventoryService.updateProductQty(productqty.getQty(),id));
        clear();

    }

    @Override
    public int getSubTotal() {
        return listProduct.values().stream().map(item->item.getPrice()* item.getQty()).reduce(0, Integer::sum)+listCombo.values().stream()
                .map(item->item.getPrice()*item.getQty()).reduce(0, Integer::sum);
    }

    @Override
    public int getCK() {
        return listProduct.values().stream().map(item->item.getPrice()* item.getQty()*item.getDiscount()/100).reduce(0, Integer::sum)+listCombo.values().stream()
                .map(item->item.getPrice()*item.getQty()*item.getDiscount()/100).reduce(0, Integer::sum);
    }

    @Override
    @Transactional
    public void fillListDetailReceipt(ReceiptEntity receiptEntity) {
        Collection<CartItem> mylistP=listProduct.values();
        Collection<CartItemCombo> mylistC=listCombo.values();
        Map<Long,DetailReceiptEntity> mymap=new HashMap<>();
        mylistP.forEach(item->{
            DBillEntity template=new DBillEntity();
            template.setQty(item.getQty());
            template.setProductId(item.getProductId());
            template.setComboId(0L);
            template.setRDb(receiptEntity);
            dBillService.saveE(template);
            DetailReceiptEntity detail=mymap.get(item.getProductId());
            if(detail==null){
                detail=new DetailReceiptEntity();
                detail.setCk(item.getDiscount());
                detail.setDrpID(productService.findOneE(item.getProductId()));
                detail.setQty(item.getQty());
                detail.setProductName(detail.getDrpID().getProduct_name());
                detail.setReceiptEntity(receiptEntity);
                detail.setCreateAt(Timestamp.from(Instant.now()));
                mymap.put(item.getProductId(),detail);
            }
            else{
                detail.setQty(detail.getQty()+item.getQty());
            }
        });
        mylistC.forEach(item -> { //item la 1 combo
            List<ComboProductEntity> list=comboService.findOneE(item.getComboId()).getCplist();
            DBillEntity template=new DBillEntity();
            template.setQty(item.getQty());
            template.setProductId(0L);
            template.setComboId(item.getComboId());
            template.setRDb(receiptEntity);
            dBillService.saveE(template);
            list.forEach(comboproduct->{ //combo product la trong combo do co product nao- soluong bao nhieu
                DetailReceiptEntity detail=mymap.get(comboproduct.getProduct().getId());
                if(detail==null){
                    detail=new DetailReceiptEntity();
                    detail.setCk(item.getDiscount());
                    detail.setDrpID(productService.findOneE(comboproduct.getProduct().getId()));
                    detail.setQty(comboproduct.getQty()* item.getQty());
                    detail.setProductName(detail.getDrpID().getProduct_name());
                    detail.setReceiptEntity(receiptEntity);
                    detail.setCreateAt(Timestamp.from(Instant.now()));
                    mymap.put(comboproduct.getProduct().getId(),detail);
                }
                else{
                    detail.setQty(detail.getQty() + item.getQty()* comboproduct.getQty());
                    //2 combo trung nhau 1sp -> giam gia bi ghi de;
                }
            });
        });
        // 1 map ds san pham + soluong

        receiptEntity.setListDetail(new ArrayList<>(mymap.values()));
    }

    public class ProductQty{
        private Long productId;
        private int qty;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }

}
