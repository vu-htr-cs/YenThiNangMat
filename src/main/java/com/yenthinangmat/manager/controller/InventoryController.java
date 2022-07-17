package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {
    @GetMapping("/admin/inventory")
    public String showInventory(){
        return "Inventory/baocaotonkho";
    }
}
