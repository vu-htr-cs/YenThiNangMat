package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.api.Output.XTNOutput;
import com.yenthinangmat.manager.entity.CtpEntity;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

public interface CtpService {
    CtpEntity saveE(CtpEntity ctpEntity);
    void saveEAll(Collection<CtpEntity> collection);
    Map<Long,XTNOutput> getXTNOutput(Date start, Date end);
}
