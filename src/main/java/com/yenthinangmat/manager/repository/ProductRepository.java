package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    void deleteById(Long id);
    ProductEntity findFirstById(Long id);
    @Query("select p from ProductEntity p where p.categoryEntity.id=?1")
    Page<ProductEntity> findByCategoryId(Long id, Pageable pageable);
    @Query("select p from ProductEntity p where p.categoryEntity.id=?1")
    List<ProductEntity> findAllByCategoryId(Long id);
}
