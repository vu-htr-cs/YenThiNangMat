package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.config.jwt.JwtTokenProvider;
import com.yenthinangmat.manager.config.jwt.UserPrinciple;
import com.yenthinangmat.manager.dto.request.SignInForm;
import com.yenthinangmat.manager.dto.response.JwtResponse;
import com.yenthinangmat.manager.service.RoleService;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authLogin( @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(),signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);
        UserPrinciple userPrinciple=(UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,userPrinciple.getUsername(),userPrinciple.getAuthorities()));
    }

}
