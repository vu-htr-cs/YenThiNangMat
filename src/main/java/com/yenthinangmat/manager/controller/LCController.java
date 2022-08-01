package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.service.LCService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LCController {
    private final LCService lcService;

    public LCController(LCService lcService) {
        this.lcService = lcService;
    }
    @GetMapping("/admin/LocationStore")
    public String getAllLC(){
        return "LC/quanlyLC";
    }

    @GetMapping("/admin/LocationStore/{id}/edit")
    public String editLC(@PathVariable(name="id") Long id, Model model){
        LocationStoreDTO res=lcService.findOne(id);
        model.addAttribute("res",res);
        return "LC/editLC";
    }
}
