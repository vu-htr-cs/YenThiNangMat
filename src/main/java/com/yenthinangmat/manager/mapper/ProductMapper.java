package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.ProductAddDTO;
import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.entity.CategoryEntity;
import com.yenthinangmat.manager.entity.DescriptionEntity;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.entity.UnitEntity;

public class ProductMapper {
    public static ProductDisplayDTO toDisplay(ProductEntity productEntity){
        ProductDisplayDTO product=new ProductDisplayDTO();
        product.setId(productEntity.getId());
        product.setProductName(productEntity.getProduct_name());
        product.setUnitName(productEntity.getUnitEntity().getName());
        product.setCategoryName(productEntity.getCategoryEntity().getName());
        product.setContent(productEntity.getDescriptionEntity().getContent());
        product.setImg(productEntity.getDescriptionEntity().getImg());
        product.setPrice(productEntity.getPrice());
        return product;
    }
    public static ProductEntity toEntity(ProductAddDTO productAddDTO, UnitEntity unitEntity, CategoryEntity categoryEntity,
                                         DescriptionEntity descriptionEntity){
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProduct_name(productAddDTO.getName());
        productEntity.setUnitEntity(unitEntity);
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setDescriptionEntity(descriptionEntity);
        productEntity.setPrice(productAddDTO.getPrice());
        return productEntity;
    }
}
