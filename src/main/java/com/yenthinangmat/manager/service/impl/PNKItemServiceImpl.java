package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.PNKItem;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.service.PNKItemService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@SessionScope
@Service
public class PNKItemServiceImpl implements PNKItemService {
    @Autowired
    private ProductService productService;

    Map<Long, PNKItem> mymap=new HashMap<>();

    @Override
    public void add(Long productid,int giavon,int soluong) {
        PNKItem cur=mymap.get(productid);
        if(cur==null){
            PNKItem newElement= new PNKItem();
            ProductEntity product=productService.findOneE(productid);
            newElement.setProductId(productid);
            newElement.setProductName(product.getProduct_name());
            newElement.setUnit(product.getUnitEntity().getName());
            newElement.setGiavon(giavon);
            newElement.setSoluong(soluong);
            mymap.put(productid,newElement);
        }
        else {
            cur.setSoluong(cur.getSoluong() + soluong);
            cur.setGiavon(cur.getGiavon()+giavon);
        }
    }

    @Override
    public void remove(Long id) {
        mymap.remove(id);
    }

    @Override
    public void clear() {
        mymap.clear();
    }

    @Override
    public Collection<PNKItem> getAllItems() {
        return mymap.values();
    }

    @Override
    public int getCount() {
        if(mymap.isEmpty()) return 0;
        return mymap.values().size();
    }
}
