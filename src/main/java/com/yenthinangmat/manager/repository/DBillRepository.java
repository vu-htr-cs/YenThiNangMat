package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.DBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBillRepository extends JpaRepository<DBillEntity,Long> {

}
