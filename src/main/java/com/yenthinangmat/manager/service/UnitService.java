package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.entity.UnitEntity;

import java.util.List;

public interface UnitService {
    List<UnitDTO> getAll();
    UnitDTO add(UnitDTO unitDTO);
    void deleteOne(Long id);
    UnitDTO findOne(Long id);
    void update(UnitDTO unitDTO);
    UnitEntity findOneE(Long id);
}
