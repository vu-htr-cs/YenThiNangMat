package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.ComboEntity;
import com.yenthinangmat.manager.repository.ComboRepository;
import com.yenthinangmat.manager.service.ComboService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ComboServiceImpl implements ComboService {
    private final ComboRepository comboRepository;

    public ComboServiceImpl(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    @Override
    @Transactional
    public ComboEntity saveE(ComboEntity comboEntity) {
        return comboRepository.save(comboEntity);
    }
}
