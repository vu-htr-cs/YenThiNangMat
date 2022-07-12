package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.InventoryEntity;
import com.yenthinangmat.manager.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {

    @Query("select i from InventoryEntity i where i.inventory_pID.id=?1 ")
    InventoryEntity findbyProductID(Long id);
}
