package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category")
    public String getAllCategory(){
        return "Category/quanlynhomhang";
    }
    @GetMapping("/admin/category/{id}/edit")
    public String editCate(@PathVariable(name="id") Long id, Model model){
        CategoryDTO res=categoryService.findOne(id);
        model.addAttribute("res",res);
        return "Category/editcategory";
    }
}
