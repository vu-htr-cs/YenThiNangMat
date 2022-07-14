package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.CartItem;
import com.yenthinangmat.manager.entity.CartItemCombo;
import com.yenthinangmat.manager.entity.ComboProductEntity;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.mapper.ComboMapper;
import com.yenthinangmat.manager.service.CartService;
import com.yenthinangmat.manager.service.ComboService;
import com.yenthinangmat.manager.service.InventoryService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.*;

@SessionScope
@Service
public class CartServiceImpl implements CartService {
    final
    InventoryService inventoryService;
    @Autowired
    ComboService comboService;
    @Autowired
    ProductService productService;

    Map<Long,CartItem> listProduct=new HashMap<>();
    Map<Long,CartItemCombo> listCombo=new HashMap<>();

    public CartServiceImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
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
        mymap.forEach((id,productqty)->{
            inventoryService.updateProductQty(productqty.getQty(),id);
        });
        clear();

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
