package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity,Long> {

}
