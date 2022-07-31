package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface InventoryService {
    InventoryEntity saveE(InventoryEntity inventory);
    List<InventoryEntity> saveAllE(List<InventoryEntity> list);
    InventoryEntity findOne(Long id);
    void updateProductQty(int soluong,Long id);
    Collection<InventoryDTO> getByPage();


}
