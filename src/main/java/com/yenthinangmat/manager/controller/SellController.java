package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.dto.CustomerDTO;
import com.yenthinangmat.manager.service.CategoryService;
import com.yenthinangmat.manager.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SellController {
    private final CategoryService categoryService;
    private final CustomerService customerService;

    public SellController(CategoryService categoryService, CustomerService customerService) {
        this.categoryService = categoryService;
        this.customerService = customerService;
    }

    @GetMapping("/employee/sell")
    public String sellProduct(Model model){
        List<CategoryDTO> categoryList= categoryService.getAll();
        List<CustomerDTO> customerL=customerService.getAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("customerL",customerL);
        return "Sell/index";
    }
}
