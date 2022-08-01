package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.RoleEntity;
import com.yenthinangmat.manager.repository.RoleRepository;
import com.yenthinangmat.manager.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    final
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String findRoleName(Long id) {
        return roleRepository.findFirstById(id).getRoleName();
    }

    @Override
    public RoleEntity findOneE(Long id) {
        return roleRepository.findFirstById(id);
    }

}
