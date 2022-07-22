package com.yenthinangmat.manager.config.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yenthinangmat.manager.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserPrinciple implements UserDetails {
    static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private byte enabled;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(Long id, String username, String password, byte enabled, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public static UserPrinciple build(UserEntity userEntity){
        List<GrantedAuthority> authorities=userEntity.getListRole().stream().
                map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new UserPrinciple(userEntity.getId(), userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), userEntity.getEnabled(),authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
