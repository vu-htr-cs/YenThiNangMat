package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryService {
    InventoryEntity saveE(InventoryEntity inventory);
    List<InventoryEntity> saveAllE(List<InventoryEntity> list);
    InventoryEntity findOne(Long id);
    void updateProductQty(int soluong,Long id);
    List<InventoryDTO> getByPage(Pageable pageable);
    long count();
}
