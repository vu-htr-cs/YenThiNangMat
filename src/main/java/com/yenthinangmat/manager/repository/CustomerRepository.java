package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    void deleteById(Long id);
    CustomerEntity findFirstById(Long id);
}
