package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.entity.InventoryEntity;

public class InventoryMapper {
    public static InventoryDTO toDTO(InventoryEntity inventoryEntity){
        InventoryDTO inventoryDTO=new InventoryDTO();
        inventoryDTO.setId(inventoryEntity.getId());
        inventoryDTO.setProductName(inventoryEntity.getInventory_pID().getProduct_name());
        inventoryDTO.setUnit(inventoryEntity.getInventory_pID().getUnitEntity().getName());
        inventoryDTO.setSoluong(inventoryEntity.getSoluong());
        inventoryDTO.setGiavon(inventoryEntity.getGiavon());
        return inventoryDTO;
    }
}
