package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.CtpEntity;
import com.yenthinangmat.manager.repository.CtpRepository;
import com.yenthinangmat.manager.service.CtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class CtpServiceImpl implements CtpService {
    @Autowired
    private CtpRepository ctpRepository;
    @Override
    @Transactional
    public CtpEntity saveE(CtpEntity ctpEntity) {
        return ctpRepository.save(ctpEntity);
    }

    @Override
    public void saveEAll(Collection<CtpEntity> collection) {
        ctpRepository.saveAll(collection);
    }
}
