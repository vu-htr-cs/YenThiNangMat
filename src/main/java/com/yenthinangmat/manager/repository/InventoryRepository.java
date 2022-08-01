package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {

    InventoryEntity findFirstById(Long id);

    @Modifying
    @Query("update InventoryEntity i set i.soluong=i.soluong-?1 where i.inventory_pID.id=?2")
    void updateProduct(int soluong,Long id);
    List<InventoryEntity> findAllById(Long id);
}
