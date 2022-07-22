package com.yenthinangmat.manager.config.jwt;

import com.yenthinangmat.manager.entity.UserEntity;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class MyUDService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userService.findOneE(username);
        if(user==null){
            System.out.println("User not found !"+username);
            throw new UsernameNotFoundException("Không tìm thấy tài khoản "+username);
        }
        return UserPrinciple.build(user);
    }
}
