package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ComboEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<ComboEntity,Long> {

}
