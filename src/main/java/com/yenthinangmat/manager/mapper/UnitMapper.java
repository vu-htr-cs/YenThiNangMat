package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.entity.UnitEntity;

public class UnitMapper {
    public static UnitDTO toDTO(UnitEntity unitEntity){
        UnitDTO unitDTO=new UnitDTO();
        unitDTO.setId(unitEntity.getId());
        unitDTO.setName(unitEntity.getName());
        unitDTO.setMota(unitEntity.getMota());
        return unitDTO;
    }
    public static UnitEntity toEntity(UnitDTO unitDTO){
        UnitEntity unitEntity=new UnitEntity();
        unitEntity.setName(unitDTO.getName());
        unitEntity.setMota(unitDTO.getMota());
        unitEntity.setId(unitDTO.getId());
        return unitEntity;
    }
}
