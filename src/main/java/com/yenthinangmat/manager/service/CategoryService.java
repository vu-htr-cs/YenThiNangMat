package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
    CategoryDTO add(CategoryDTO categoryDTO);
    void deleteOne(Long id);
    CategoryDTO findOne(Long id);
    void update(CategoryDTO categoryDTO);
    CategoryEntity findOneE(Long id);
    List<CategoryDTO> getByPage(Pageable pageable);
    long count();
}
