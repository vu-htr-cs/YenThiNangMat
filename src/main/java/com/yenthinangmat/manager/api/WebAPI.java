package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.config.jwt.JwtTokenProvider;
import com.yenthinangmat.manager.config.jwt.UserPrinciple;
import com.yenthinangmat.manager.dto.request.SignInForm;
import com.yenthinangmat.manager.dto.response.JwtResponse;
import com.yenthinangmat.manager.service.RoleService;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAPI {
    final
    UserService userService;
    final
    RoleService roleService;
    final
    PasswordEncoder passwordEncoder;
    final
    AuthenticationManager authenticationManager;
    final
    JwtTokenProvider jwtTokenProvider;

    public WebAPI(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authLogin( @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(),signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);
        UserPrinciple userPrinciple=(UserPrinciple) authentication.getPrincipal();
        HttpHeaders headers= new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);
        headers.add("Set-cookie","jwt="+token);
        return ResponseEntity.ok().headers(headers).body(new JwtResponse(userPrinciple.getUsername(),userPrinciple.getAuthorities()));
    }

}
