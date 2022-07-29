package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity,Long> {
    ReceiptEntity findFirstById(Long id);
}
