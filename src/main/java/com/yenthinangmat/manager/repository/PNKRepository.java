package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.PNKEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PNKRepository extends JpaRepository<PNKEntity,Long> {

}
