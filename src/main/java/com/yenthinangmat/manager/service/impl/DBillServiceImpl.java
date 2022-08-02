package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.DBillEntity;
import com.yenthinangmat.manager.repository.DBillRepository;
import com.yenthinangmat.manager.service.DBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBillServiceImpl implements DBillService {
    @Autowired
    DBillRepository dBillRepository;
    @Override
    public DBillEntity saveE(DBillEntity dBillEntity) {
        return dBillRepository.save(dBillEntity);
    }
}
