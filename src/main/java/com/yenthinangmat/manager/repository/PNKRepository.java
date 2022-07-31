package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.PNKEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PNKRepository extends JpaRepository<PNKEntity,Long> {
    @Query("select pnk from PNKEntity pnk where pnk.ngayGhiSo>?1 and pnk.ngayGhiSo<?2 ")
    List<PNKEntity> getFromStartToEnd(Date start,Date end);

}
