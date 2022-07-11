package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.Component;

import java.util.Collection;

public interface ComboTempService {
    void add(Component component);

    void subtract(Long id);

    void remove(Long id);

    void clear();

    Collection<Component> getAllItems();

    int getCount();

}
