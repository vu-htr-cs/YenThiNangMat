package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.entity.CategoryEntity;

public class CategoryMapper {
    public static CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO category=new CategoryDTO();
        category.setId(categoryEntity.getId());
        category.setCode(categoryEntity.getCode());
        category.setMota(categoryEntity.getMota());
        category.setName(categoryEntity.getName());
        return category;
    }
    public static CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setCode(categoryDTO.getCode());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setMota(categoryDTO.getMota());
        categoryEntity.setId(categoryDTO.getId());
        return  categoryEntity;
    }
}
