package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/login")
    public String loginPage(){
        return "/LGLO/login";
    }

}
