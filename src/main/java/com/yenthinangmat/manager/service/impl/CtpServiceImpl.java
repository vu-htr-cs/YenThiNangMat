package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.api.Output.ComboProfitOutput;
import com.yenthinangmat.manager.api.Output.ProfitOutput;
import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.entity.*;
import com.yenthinangmat.manager.repository.CtpRepository;
import com.yenthinangmat.manager.service.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CtpServiceImpl implements CtpService {
    private final CtpRepository ctpRepository;
    private final PNKService pnkService;
    final
    DetailReceiptService detailReceiptService;
    final
    ProductService productService;
    final
    InventoryService inventoryService;
    final
    ReceiptService receiptService;
    final
    ComboService comboService;

    public CtpServiceImpl(CtpRepository ctpRepository, PNKService pnkService, DetailReceiptService detailReceiptService, ProductService productService, InventoryService inventoryService, ReceiptService receiptService, ComboService comboService) {
        this.ctpRepository = ctpRepository;
        this.pnkService = pnkService;
        this.detailReceiptService = detailReceiptService;
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.receiptService = receiptService;
        this.comboService = comboService;
    }

    @Override
    @Transactional
    public CtpEntity saveE(CtpEntity ctpEntity) {
        return ctpRepository.save(ctpEntity);
    }

    @Override
    public void saveEAll(Collection<CtpEntity> collection) {
        ctpRepository.saveAll(collection);
    }

    @Override
    public Map<Long, XNTOutput> getXTNOutput(Date start, Date end) {
        List<PNKEntity> temp = pnkService.getFromStartToEnd(start, end);//pnk trong khoang start-end
        Map<Long, XNTOutput> mymap = new HashMap<>();

        temp.forEach(item -> {
            List<Object[]> myarr = ctpRepository.getAllByPNKGrb(item.getId());
            myarr.forEach(objectarr -> {
                        XNTOutput xtn = mymap.get((Long) objectarr[0]);
                        if (xtn == null) {
                            xtn = new XNTOutput();
                            xtn.setProductId((long) objectarr[0]);
                            xtn.setQty((long) objectarr[1]);

                            xtn.setGiaVon(inventoryService.layGiaVon(xtn.getProductId()));
                            xtn.setName(productService.findOneE(xtn.getProductId()).getProduct_name());
                            xtn.setDvt(productService.findOneE(xtn.getProductId()).getUnitEntity().getName());
                            mymap.put(xtn.getProductId(), xtn);
                        } else {
                            xtn.setQty(xtn.getQty() + (long) objectarr[1]);

                        }
                    }
            );
        });
        List<Object[]> temp123 = detailReceiptService.getAllProductX(new Timestamp(start.getTime()), new Timestamp(end.getTime()));
        temp123.forEach(arr -> {
            XNTOutput product = mymap.get((Long) arr[0]);
            if (product != null) {
                product.setSlXuat(product.getSlXuat() + (long) arr[1]);
            } else {
                product = new XNTOutput();
                product.setProductId((long) arr[0]);// set Id
                product.setQty(0L);

                product.setGiaVon(inventoryService.layGiaVon(product.getProductId()));
//                product.setTongnhap((long)productService.findOneE(product.getProductId()).getPrice());//set gia von
                product.setName(productService.findOneE(product.getProductId()).getProduct_name());//set name
                product.setDvt(productService.findOneE(product.getProductId()).getUnitEntity().getName());//set dvt
                product.setSlXuat((long) arr[1]);

                mymap.put(product.getProductId(),product);
            }
        });
        return mymap;//san pham, so luong gia von trong start-end
    }
    @Override
    public Map<Long, ProfitOutput> getProfit(Date start, Date end) {
        List<PNKEntity> temp = pnkService.getFromStartToEnd(start, end);//pnk trong khoang start-end
        Map<Long, ProfitOutput> mymap = new HashMap<>();
        temp.forEach(item -> {
            List<Object[]> myarr = ctpRepository.getAllByPNKGrb(item.getId());
            myarr.forEach(objectarr -> {
                        ProfitOutput profit = mymap.get((Long) objectarr[0]);
                        if (profit == null) {
                            profit = new ProfitOutput();
                            profit.setProductId((long) objectarr[0]);
                            profit.setQty((long) objectarr[1]);
                            profit.setGiaVon(inventoryService.layGiaVon(profit.getProductId()));
                            profit.setName(productService.findOneE(profit.getProductId()).getProduct_name());
                            profit.setDvt(productService.findOneE(profit.getProductId()).getUnitEntity().getName());
                            mymap.put(profit.getProductId(), profit);
                        } else {
                            profit.setQty(profit.getQty() + (long) objectarr[1]);

                        }
                    }
            );
        });
        //Luu tru tien hang ban dc
        List<DetailReceiptEntity> temp123 = detailReceiptService.getAllProductProfit(new Timestamp(start.getTime()), new Timestamp(end.getTime()));
        temp123.forEach(item -> {
            ProfitOutput product = mymap.get(item.getDrpID().getId());
            ProductEntity pe = productService.findOneE(item.getDrpID().getId());
            if (product == null) {
                product = new ProfitOutput();
                product.setProductId(item.getDrpID().getId());
                product.setQty(0L);//den buoc nay ma null thi tuc la 0 nhap
                product.setGiaVon(inventoryService.layGiaVon(product.getProductId()));
                product.setName(pe.getProduct_name());//set name
                product.setDvt(pe.getUnitEntity().getName());//set dvt

                product.setSlBan(item.getQty());
                product.setTienBan((1 - item.getCk() / 100) * (pe.getPrice() * item.getQty()));
                mymap.put(product.getProductId(),product);
            } else {
                product.setSlBan(product.getSlBan() + item.getQty());
                product.setTienBan(product.getTienBan() + (1 - item.getCk() / 100) * (pe.getPrice() * item.getQty()));
            }
        });
        return mymap;
    }
    public List<ComboProfitOutput> getComboProfit(Date start,Date end){
        List<ReceiptEntity> listReceipt = receiptService.getEntity(new Timestamp(start.getTime()), new Timestamp(end.getTime()));
        //chac chan co combo trung nhau => map
        Map<Long,ComboProfitOutput> mymap=new HashMap<>();
        listReceipt.forEach(item-> item.getListDb().forEach(product->{
            if(product.getComboId()!=0){//neu nhu la combo
                ComboProfitOutput combo=mymap.get(product.getComboId());
                if(combo==null){
                    ComboEntity cb=comboService.findOneE(product.getComboId());
                    combo=new ComboProfitOutput();
                    combo.setComboName(cb.getComboName());
                    combo.setSlBan(product.getQty());
                    int sum=0;
                    for(ComboProductEntity cpe :cb.getCplist()){
                        sum+=cpe.getQty()*cpe.getProduct().getPrice();
                    }
                    combo.setChenhLechGia( cb.getPrice() - sum  );
                    mymap.put(product.getComboId(), combo);
                }
                else{
                    combo.setSlBan(combo.getSlBan()+product.getQty());
                }

            }
        }));
        return new ArrayList<>(mymap.values());

    }

}
