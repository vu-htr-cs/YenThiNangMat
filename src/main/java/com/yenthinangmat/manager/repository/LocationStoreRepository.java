package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.LocationStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationStoreRepository extends JpaRepository<LocationStoreEntity,Long> {
    List<LocationStoreEntity> findAll();
    void deleteById(Long id);
    LocationStoreEntity findFirstById(Long id);
}
