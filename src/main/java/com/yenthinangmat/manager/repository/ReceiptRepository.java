package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity,Long> {
    ReceiptEntity findFirstById(Long id);
    void deleteById(Long id);
    @Query("select re from ReceiptEntity re where re.ngayLap >?1 and re.ngayLap < ?2 order by re.ngayLap desc")
    List<ReceiptEntity> getAllFromStarToEnd(Timestamp start,Timestamp end);
}
