package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    void deleteById(Long id);
    ProductEntity findFirstById(Long id);
}
