package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;
import com.yenthinangmat.manager.mapper.InventoryMapper;
import com.yenthinangmat.manager.repository.InventoryRepository;
import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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
    public Collection<InventoryDTO> getByPage() {
        List<InventoryEntity> temp =inventoryRepository.findAll().stream().toList();
        List<InventoryDTO> res=new ArrayList<>();
        Map<Long,InventoryDTO> mymap=new HashMap<>();
        temp.forEach(item->{
            InventoryDTO inventoryDTO= mymap.get(item.getInventory_pID().getId());
            if(inventoryDTO==null){
                //Tao moi, tong ton kho = giavon*soluong;
                InventoryDTO newInventory=InventoryMapper.toDTO(item);
                newInventory.setTienTonKho(item.getGiavon()*item.getSoluong());
                mymap.put(item.getInventory_pID().getId(),newInventory);
            }
            else{
                inventoryDTO.setSoluong(inventoryDTO.getSoluong()+ item.getSoluong());
                inventoryDTO.setTienTonKho(inventoryDTO.getTienTonKho()+ item.getSoluong()*item.getGiavon());
            }
        });
        return mymap.values();
    }

}
