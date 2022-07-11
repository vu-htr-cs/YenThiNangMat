package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @Override
    List<CategoryEntity> findAll();
    void deleteById(Long id);
    CategoryEntity findFirstById(Long id);
}
