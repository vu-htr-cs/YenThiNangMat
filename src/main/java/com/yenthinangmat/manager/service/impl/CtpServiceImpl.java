package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.entity.CtpEntity;
import com.yenthinangmat.manager.entity.PNKEntity;
import com.yenthinangmat.manager.repository.CtpRepository;
import com.yenthinangmat.manager.service.CtpService;
import com.yenthinangmat.manager.service.DetailReceiptService;
import com.yenthinangmat.manager.service.PNKService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CtpServiceImpl implements CtpService {
    private final CtpRepository ctpRepository;
    private final PNKService pnkService;
    final
    DetailReceiptService detailReceiptService;
    final
    ProductService productService;

    public CtpServiceImpl(CtpRepository ctpRepository, PNKService pnkService, DetailReceiptService detailReceiptService, ProductService productService) {
        this.ctpRepository = ctpRepository;
        this.pnkService = pnkService;
        this.detailReceiptService = detailReceiptService;
        this.productService = productService;
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
                        XNTOutput xtn = mymap.get((Long)objectarr[0]);
                        if (xtn == null) {
                            xtn=new XNTOutput();
                            xtn.setProductId((long)objectarr[0]);
                            xtn.setQty((long) objectarr[1]);
                            xtn.setTongnhap((long) objectarr[2]);
                            xtn.setName(productService.findOneE(xtn.getProductId()).getProduct_name() );
                            xtn.setDvt(productService.findOneE(xtn.getProductId()).getUnitEntity().getName());
                            mymap.put(xtn.getProductId(),xtn);
                        }
                        else {
                            xtn.setQty(xtn.getQty()+(long) objectarr[1]);
                            xtn.setTongnhap(xtn.getTongnhap()+(long) objectarr[2]);
                        }
                    }
            );
        });
        List<Object[]> temp123= detailReceiptService.getAllProductX(new Timestamp(start.getTime()),new Timestamp(end.getTime()));
        temp123.forEach(arr->{
            XNTOutput product=mymap.get((Long)arr[0]);
            if(product!=null){
                product.setSlXuat(product.getSlXuat()+(long) arr[1]);
            }
            else{
                product=new XNTOutput();
                product.setProductId((long)arr[0]);// set Id
                product.setQty(0L);
                product.setTongnhap((long)productService.findOneE(product.getProductId()).getPrice());//set gia von
                product.setName(productService.findOneE(product.getProductId()).getProduct_name() );
                product.setDvt(productService.findOneE(product.getProductId()).getUnitEntity().getName());
                product.setSlXuat((long) arr[1]);
            }
        });
        return mymap;//san pham, so luong gia von trong start-end
    }
}
