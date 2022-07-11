package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.DescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<DescriptionEntity,Long> {
    DescriptionEntity findFirstById(Long id);
}
