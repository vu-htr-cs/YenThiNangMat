package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.RoleEntity;

public interface RoleService {
    String findRoleName(Long id);
    RoleEntity findOneE(Long id);
}
