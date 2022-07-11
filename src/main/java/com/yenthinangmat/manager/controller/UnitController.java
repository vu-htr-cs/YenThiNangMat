package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }
    @GetMapping("/admin/UnitOfMeasure")
    public String getAllUnit(){
        return "/UNO/quanlydvt";
    }
    @GetMapping("/admin/UnitOfMeasure/{id}/edit")
    public String editUnit(@PathVariable(name="id") Long id, Model model){
        UnitDTO unitDTO=unitService.findOne(id);
        model.addAttribute("res",unitDTO);
        return "UNO/editUNO";
    }
}
