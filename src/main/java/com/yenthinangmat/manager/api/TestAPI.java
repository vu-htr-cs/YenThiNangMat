package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.UserRoleDTO;
import com.yenthinangmat.manager.entity.UserEntity;
import com.yenthinangmat.manager.repository.UserRepository;
import com.yenthinangmat.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {
    @Autowired
    UserService userService;

    @GetMapping("/api/user")
    public UserRoleDTO test(){
        return userService.findOne("nguyenheo33");
    }
}
