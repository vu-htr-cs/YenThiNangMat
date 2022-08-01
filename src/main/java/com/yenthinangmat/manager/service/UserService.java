package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.UserRoleDTO;
import com.yenthinangmat.manager.entity.UserEntity;

public interface UserService {
    UserEntity findOneE(String username);
    UserRoleDTO findOne(String username);
    boolean existByUsername(String username);
    UserEntity saveE(UserEntity user);
    void deleteOne(Long id);
}
