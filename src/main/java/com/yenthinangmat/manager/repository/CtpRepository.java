package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.CtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtpRepository extends JpaRepository<CtpEntity,Long> {

}
