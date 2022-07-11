package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ComboProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboProductRepository extends JpaRepository<ComboProductEntity,Long> {

}
