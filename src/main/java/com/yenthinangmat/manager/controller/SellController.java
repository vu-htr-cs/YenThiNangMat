package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SellController {
    private final CategoryService categoryService;

    public SellController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/employee/sell")
    public String sellProduct(Model model){
        List<CategoryDTO> categoryList= categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        return "Sell/index";
    }
}
