package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DetailReceiptRepository extends JpaRepository<DetailReceiptEntity,Long> {
    @Query("select de from DetailReceiptEntity de where de.receiptEntity.id=?1")
    List<DetailReceiptEntity> findAllByRID(Long id);

    @Query(" select de.drpID.id,sum(de.qty)from DetailReceiptEntity  de where de.createAt>?1 and de.createAt<?2 group by de.drpID.id")
    List<Object[]> getAllProductX(Timestamp start, Timestamp end);
}
