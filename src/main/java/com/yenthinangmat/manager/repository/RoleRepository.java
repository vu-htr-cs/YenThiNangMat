package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findFirstById(Long id);
}
