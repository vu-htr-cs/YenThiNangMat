package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity,Long> {
    @Override
    List<UnitEntity> findAll();
    void deleteById(Long id);
    UnitEntity findFirstById(Long id);
}
