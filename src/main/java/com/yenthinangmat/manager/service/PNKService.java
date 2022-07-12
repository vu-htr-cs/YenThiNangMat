package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.entity.PNKEntity;

import java.util.List;

public interface PNKService {
    PNKEntity save(PNKEntity pnk);
    List<PNKDTO> getAll();
}
