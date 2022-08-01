package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.UserRoleDTO;
import com.yenthinangmat.manager.entity.RoleEntity;
import com.yenthinangmat.manager.entity.UserEntity;
import com.yenthinangmat.manager.repository.UserRepository;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserEntity findOneE(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    @Transactional
    public UserRoleDTO findOne(String username) {
        UserRoleDTO res=new UserRoleDTO();
        UserEntity user= userRepository.findFirstByUsername(username);
        if(user!=null){
            res.setUsername(user.getUsername());
            res.setPassword(user.getPassword());
            res.setEnabled(user.getEnabled());
            res.setRole(user.getListRole().stream().map(RoleEntity::getRoleName).collect(Collectors.toList()));
            return res;
        }else return null;
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity saveE(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        userRepository.deleteAllById(id);
    }

}
