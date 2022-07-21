package com.yenthinangmat.manager.config;

import com.yenthinangmat.manager.dto.UserRoleDTO;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRoleDTO user=userService.findOne(username);
        if(user==null){
            System.out.println("User not found !"+username);
            throw new UsernameNotFoundException("Không tìm thấy tài khoản "+username);
        }
        List<String> roleNames=user.getRole();
        List<GrantedAuthority> grantList= new ArrayList<>();
        if(roleNames!=null){
            for(String role:roleNames){
                GrantedAuthority authority= new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),grantList);
    }
}
