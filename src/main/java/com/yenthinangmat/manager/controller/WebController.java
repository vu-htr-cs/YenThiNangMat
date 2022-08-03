package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping(value={"/","/login"})
    public String loginPage(){
        return "/LGLO/login";
    }
    @GetMapping("/signup")
    public String signUpPage(){
        return "/LGLO/signup";
    }
}
