package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XNTController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/admin/xnt/{page}")
    public String getPage(){

        return"XNT/index";
    }
}
