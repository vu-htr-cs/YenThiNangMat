package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.CtpEntity;

import java.util.Collection;

public interface CtpService {
    CtpEntity saveE(CtpEntity ctpEntity);
    void saveEAll(Collection<CtpEntity> collection);
}
