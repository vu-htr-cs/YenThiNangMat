package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDisplayDTO> showAllProduct();
    ProductDisplayDTO saveA(ProductEntity productEntity);
    void deleteOne(Long id);
    ProductDisplayDTO findOne(Long id);
    void update(ProductEntity productEntity);
    ProductEntity findOneE(Long id);
    List<ProductDisplayDTO> getListProduct(Pageable pageable);
    long count();
    List<ProductDisplayDTO> getAll();
}
