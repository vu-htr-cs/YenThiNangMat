package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.config.jwt.JwtTokenProvider;
import com.yenthinangmat.manager.config.jwt.UserPrinciple;
import com.yenthinangmat.manager.dto.request.SignInForm;
import com.yenthinangmat.manager.dto.request.SignUpForm;
import com.yenthinangmat.manager.dto.response.JwtResponse;
import com.yenthinangmat.manager.dto.response.ResponseMessage;
import com.yenthinangmat.manager.entity.RoleEntity;
import com.yenthinangmat.manager.entity.UserEntity;
import com.yenthinangmat.manager.service.RoleService;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> authLogin( @RequestBody SignInForm signInForm) throws Exception{
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInForm.getUsername(),signInForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtTokenProvider.generateToken(authentication);
            UserPrinciple userPrinciple=(UserPrinciple) authentication.getPrincipal();
            HttpHeaders headers= new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, token);
            headers.add("Set-cookie","jwt=Bearer "+token+";Max-Age=86400;Path=/;Secure;HttpOnly");
            return ResponseEntity.ok().headers(headers).body(new JwtResponse(userPrinciple.getUsername(),userPrinciple.getAuthorities()));
        }catch (BadCredentialsException e){
            throw new Exception("Sai tài khoản hoặc mật khẩu");
        }
    }
    @PostMapping("/api/admin/signup")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm){
        if(userService.existByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Tài khoản đã tồn tại! Vui lòng thử lại!"), HttpStatus.OK);
        }
        UserEntity user= new UserEntity();
        user.setUsername(signUpForm.getUsername());
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        user.setEnabled((byte)1);
        List<RoleEntity> temp=new ArrayList<>();
        temp.add(roleService.findOneE(3L));
        user.setListRole(temp);
        userService.saveE(user);
        return new ResponseEntity<>(new ResponseMessage("Tạo thành công!"),HttpStatus.OK);
    }
    @PostMapping("/api/auth/chgpasswd")
    public ResponseEntity<?> changePassword(@RequestBody SignInForm signInForm, @RequestHeader("Cookie") String cookie){
        String token=null;
        String[] rawCookieParams=cookie.split(";");
        for(String temp:rawCookieParams){
            if(temp.trim().startsWith("jwt")){
                token=temp.trim().replace("jwt=","");
            }
        }
        if(token!=null&&token.startsWith("Bearer")){
            token= token.replace("Bearer","").trim();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String username= jwtTokenProvider.getUserNameFromJWT(token.replace("Bearer","").trim());
        UserEntity user=userService.findOneE(username);
        user.setPassword(passwordEncoder.encode(signInForm.getPassword()));
        userService.saveE(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
