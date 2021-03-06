package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.PnkAddDTO;
import com.yenthinangmat.manager.entity.PNKItem;

import java.util.Collection;

public interface PNKItemService{
    void add(Long productid,int giavon,int soluong);

    void remove(Long id);

    void clear();

    Collection<PNKItem> getAllItems();

    int getCount();
    int getSum();
    void save(PnkAddDTO pnkAddDTO);
}
