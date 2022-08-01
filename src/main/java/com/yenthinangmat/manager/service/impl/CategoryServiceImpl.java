package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.entity.CategoryEntity;
import com.yenthinangmat.manager.mapper.CategoryMapper;
import com.yenthinangmat.manager.repository.CategoryRepository;
import com.yenthinangmat.manager.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryDTO> getAll(){
        List<CategoryDTO> res= new ArrayList<>();
        List<CategoryEntity> truyvan= categoryRepository.findAll();
        truyvan.forEach((item)->res.add(CategoryMapper.toDTO(item)));
        return res;
    }

    @Override
    @Transactional
    public CategoryDTO add(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity=CategoryMapper.toEntity(categoryDTO);
        categoryEntity.setCreatedAt(Timestamp.from(Instant.now()));
        CategoryEntity ce=categoryRepository.save(categoryEntity);
        CategoryDTO newCategoryDTO= CategoryMapper.toDTO(ce);
        return newCategoryDTO;
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO findOne(Long id) {
        CategoryEntity ce=categoryRepository.findFirstById(id);
        return CategoryMapper.toDTO(ce);
    }

    @Override
    @Transactional
    public void update(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity=CategoryMapper.toEntity(categoryDTO);
        categoryEntity.setModifiedAt(Timestamp.from(Instant.now()));
        categoryRepository.save(categoryEntity);

    }

    @Override
    public CategoryEntity findOneE(Long id) {
        return categoryRepository.findFirstById(id);
    }

    @Override
    public List<CategoryDTO> getByPage(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream().map(item->CategoryMapper.toDTO(item)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }
}
