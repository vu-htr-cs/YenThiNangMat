package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.entity.LocationStoreEntity;

public class LCMapper {
    public static LocationStoreDTO toDTO(LocationStoreEntity locationStoreEntity){
        LocationStoreDTO lc=new LocationStoreDTO();
        lc.setId(locationStoreEntity.getId());
        lc.setCode(locationStoreEntity.getCode());
        lc.setMota(locationStoreEntity.getMota());
        lc.setName(locationStoreEntity.getName());
        return lc;
    }
    public static LocationStoreEntity toEntity(LocationStoreDTO locationStoreDTO){
        LocationStoreEntity locationStoreEntity=new LocationStoreEntity();
        locationStoreEntity.setCode(locationStoreDTO.getCode());
        locationStoreEntity.setName(locationStoreDTO.getName());
        locationStoreEntity.setMota(locationStoreDTO.getMota());
        locationStoreEntity.setId(locationStoreDTO.getId());
        return  locationStoreEntity;
    }
}
