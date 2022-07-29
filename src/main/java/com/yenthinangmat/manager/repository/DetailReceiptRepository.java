package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailReceiptRepository extends JpaRepository<DetailReceiptEntity,Long> {
}
