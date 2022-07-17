package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;
import com.yenthinangmat.manager.mapper.InventoryMapper;
import com.yenthinangmat.manager.repository.InventoryRepository;
import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryEntity saveE(InventoryEntity inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<InventoryEntity> saveAllE(List<InventoryEntity> list) {
        return inventoryRepository.saveAll(list);
    }

    @Override
    public InventoryEntity findOne(Long id) {
        return inventoryRepository.findbyProductID(id);
    }

    @Override
    @Transactional
    public void updateProductQty(int soluong, Long id) {
        inventoryRepository.updateProduct(soluong,id);
    }

    @Override
    public List<InventoryDTO> getByPage(Pageable pageable) {
        List<InventoryEntity> temp =inventoryRepository.findAll(pageable).stream().toList();
        List<InventoryDTO> res=new ArrayList<>();
        temp.forEach(item->res.add(InventoryMapper.toDTO(item)));
        return res;
    }

    @Override
    public long count() {
        return inventoryRepository.count();
    }
}
