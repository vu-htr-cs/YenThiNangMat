package com.yenthinangmat.manager.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
public class JwtResponse {

    private String type="Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> roles;
    public JwtResponse(String username, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.roles = roles;
    }
}
