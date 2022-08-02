package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;
import com.yenthinangmat.manager.mapper.InventoryMapper;
import com.yenthinangmat.manager.repository.InventoryRepository;
import com.yenthinangmat.manager.service.DetailReceiptService;
import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {
    final
    DetailReceiptService detailReceiptService;
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, DetailReceiptService detailReceiptService) {
        this.inventoryRepository = inventoryRepository;
        this.detailReceiptService = detailReceiptService;
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
        return inventoryRepository.findFirstById(id);
    }


    @Override
    @Transactional
    public void updateProductQty(int soluong, Long id) {
        List<InventoryEntity> temp = inventoryRepository.findAllByPrdID(id);
        for (int i = 0; i < temp.size(); i++) {
            if (i != temp.size() - 1) {
                if (soluong < temp.get(i).getSoluong()) {
                    inventoryRepository.updateById(soluong, temp.get(i).getId());
                    break;
                }
                if (soluong == temp.get(i).getSoluong()) {
                    inventoryRepository.deleteById(temp.get(i).getId());

                    break;
                }
                if (soluong > temp.get(i).getSoluong()) {
                    inventoryRepository.deleteById(temp.get(i).getId());
                    soluong -= temp.get(i).getSoluong();
                }
            }
            else {
                if (soluong == temp.get(i).getSoluong()) {
                    inventoryRepository.deleteById(temp.get(i).getId());
                } else {
                    inventoryRepository.updateById(soluong, temp.get(i).getId());
                }
            }
        }
    }

    @Override
    public Collection<InventoryDTO> getByPage() {
        List<InventoryEntity> temp = inventoryRepository.findAll().stream().toList();
        List<InventoryDTO> res = new ArrayList<>();
        Map<Long, InventoryDTO> mymap = new HashMap<>();
        temp.forEach(item -> {
            InventoryDTO inventoryDTO = mymap.get(item.getInventory_pID().getId());
            if (inventoryDTO == null) {
                //Tao moi, tong ton kho = giavon*soluong;
                InventoryDTO newInventory = InventoryMapper.toDTO(item);
                newInventory.setTienTonKho(item.getGiavon() * item.getSoluong());
                mymap.put(item.getInventory_pID().getId(), newInventory);
            } else {
                inventoryDTO.setSoluong(inventoryDTO.getSoluong() + item.getSoluong());
                inventoryDTO.setTienTonKho(inventoryDTO.getTienTonKho() + item.getSoluong() * item.getGiavon());
            }
        });
        return mymap.values();
    }

    @Override
    public List<InventoryEntity> findAllE(Long id) {
        return inventoryRepository.findAllById(id);
    }

    @Override
    public  int layGiaVon(Long id) {
         List<InventoryEntity> res= inventoryRepository. findAllBypID(id);
         int sum=0;
         int slhang=0;
         for(InventoryEntity item:res){
             sum+=item.getGiavon()*item.getSoluong();
             slhang+=item.getSoluong();
         }
         if(slhang!=0){
             return sum/slhang;
         }
         else return 0;
    }
}
