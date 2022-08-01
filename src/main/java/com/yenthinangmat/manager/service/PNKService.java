package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.entity.PNKEntity;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface PNKService {
    PNKEntity save(PNKEntity pnk);
    List<PNKDTO> getAll();
    List<PNKEntity> getFromStartToEnd(Date start, Date end);
    List<PNKDTO> getByPage(Pageable pageable);
    long count();
}
