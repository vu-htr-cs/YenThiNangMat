package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.api.Output.ComboProfitOutput;
import com.yenthinangmat.manager.api.Output.ProfitOutput;
import com.yenthinangmat.manager.api.Output.XNTOutput;
import com.yenthinangmat.manager.entity.CtpEntity;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CtpService {
    CtpEntity saveE(CtpEntity ctpEntity);
    void saveEAll(Collection<CtpEntity> collection);
    Map<Long, XNTOutput> getXTNOutput(Date start, Date end);
    Map<Long, ProfitOutput> getProfit(Date start, Date end);
    List<ComboProfitOutput> getComboProfit(Date start, Date end);


}
