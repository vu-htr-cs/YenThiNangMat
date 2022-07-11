package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.ComboProductEntity;
import com.yenthinangmat.manager.repository.ComboProductRepository;
import com.yenthinangmat.manager.service.CPService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CPServiceImpl implements CPService {
    private final ComboProductRepository comboProductRepository;

    public CPServiceImpl(ComboProductRepository comboProductRepository) {
        this.comboProductRepository = comboProductRepository;
    }

    @Override
    @Transactional
    public ComboProductEntity saveE(ComboProductEntity comboProductEntity) {
        return comboProductRepository.save(comboProductEntity);
    }
}
