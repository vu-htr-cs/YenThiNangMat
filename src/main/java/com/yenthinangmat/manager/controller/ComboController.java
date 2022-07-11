package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComboController {
    @GetMapping("/admin/combo/add")
    public String addCombo(){
        return "Combo/addCombo";
    }

}
