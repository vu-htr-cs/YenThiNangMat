package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/profile/passwd")
    public String changePasswd(){
        return "Account/changepasswd";
    }
}
