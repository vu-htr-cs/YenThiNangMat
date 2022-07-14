package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.ComboDTO;
import com.yenthinangmat.manager.entity.ComboEntity;

public class ComboMapper {
    public static ComboDTO toDTO(ComboEntity comboEntity){
        ComboDTO comboDTO=new ComboDTO();
        comboDTO.setId(comboEntity.getId());
        comboDTO.setName(comboEntity.getComboName());
        comboDTO.setPrice(comboEntity.getPrice());
        return comboDTO;
    }
}
