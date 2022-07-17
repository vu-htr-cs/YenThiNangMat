package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.CategoryDTO;
import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.service.CategoryService;
import com.yenthinangmat.manager.service.ProductService;
import com.yenthinangmat.manager.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;
    private final UnitService unitService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, UnitService unitService, CategoryService categoryService) {
        this.productService = productService;
        this.unitService = unitService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public String showProduct(Model model){
        List<UnitDTO> listUnit=unitService.getAll();
        List<CategoryDTO> listCa=categoryService.getAll();
        model.addAttribute("listUnit",listUnit);
        model.addAttribute("listCa",listCa);
        return "Product/quanlysp";
    }
    @GetMapping("/product/{id}/edit")
    public String editCate(@PathVariable(name="id") Long id, Model model){
        ProductDisplayDTO displayDTO = productService.findOne(id);

        List<UnitDTO> listUnit=unitService.getAll();
        List<CategoryDTO> listCa=categoryService.getAll();
        model.addAttribute("product",displayDTO);
        model.addAttribute("listUnit",listUnit);
        model.addAttribute("listCa",listCa);

        return "Product/editProduct";
    }
}
