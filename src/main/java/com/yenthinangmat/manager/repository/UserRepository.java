package com.yenthinangmat.manager.repository;

import com.yenthinangmat.manager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findFirstByUsername(String username);
    boolean existsByUsername(String username);
    void deleteAllById(Long id);
}
