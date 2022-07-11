package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.Component;
import com.yenthinangmat.manager.service.ComboTempService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ComboTempServiceImpl implements ComboTempService {
    private Map<Long,Component> mymap= new HashMap<>();
    @Override
    public void add(Component component) {
        Component cp=mymap.get(component.getProductID());
        if(cp==null){
            mymap.put(component.getProductID(),component);
        }
        else cp.setQty(cp.getQty()+1);
    }

    @Override
    public void subtract(Long id) {
        Component cp=mymap.get(id);
        cp.setQty(cp.getQty()-1);
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
    public Collection<Component> getAllItems() {
        return mymap.values();
    }

    @Override
    public int getCount() {
        if(mymap.isEmpty()) return 0;
        return mymap.values().size();
    }
}
