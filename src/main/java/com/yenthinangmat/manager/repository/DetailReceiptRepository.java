package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailReceiptRepository extends JpaRepository<DetailReceiptEntity,Long> {
    @Query("select de from DetailReceiptEntity de where de.receiptEntity.id=?1")
    List<DetailReceiptEntity> findAllByRID(Long id);
}
