package com.yenthinangmat.manager.config;

import com.yenthinangmat.manager.entity.RoleEntity;
import com.yenthinangmat.manager.entity.UserEntity;
import com.yenthinangmat.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepository.findFirstByUsername(username);
        if(user==null){
            System.out.println("User not found !"+username);
            throw new UsernameNotFoundException("Không tìm thấy tài khoản "+username);
        }
        List<String> roleNames=user.getListRole().stream().map(RoleEntity::getRoleName).toList();
        List<GrantedAuthority> grantList= new ArrayList<>();
        if(roleNames!=null){
            for(String role:roleNames){
                GrantedAuthority authority= new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        return new User(user.getUsername(),user.getPassword(),grantList);
    }
}
