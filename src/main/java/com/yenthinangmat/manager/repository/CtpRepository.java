package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.CtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CtpRepository extends JpaRepository<CtpEntity,Long> {
    @Query("select ctp.productCtp.id,sum(ctp.soluong),sum(ctp.soluong*ctp.giaVon) from CtpEntity ctp where ctp.pnk.id=?1 group by ctp.productCtp.id")
    List<Object[]> getAllByPNKGrb(Long id);
}
