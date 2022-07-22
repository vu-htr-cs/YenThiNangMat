package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import com.yenthinangmat.manager.dto.ProviderDTO;
import com.yenthinangmat.manager.service.LCService;
import com.yenthinangmat.manager.service.ProductService;
import com.yenthinangmat.manager.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PNKController {
    final
    LCService lcService;
    final
    ProviderService providerService;
    final
    ProductService productService;

    public PNKController(LCService lcService, ProviderService providerService, ProductService productService) {
        this.lcService = lcService;
        this.providerService = providerService;
        this.productService = productService;
    }

    @GetMapping("/employee/pnk")
    public String addPNK(Model model){
        List<ProductDisplayDTO> productL=productService.getAll();
        List<ProviderDTO> nccL=providerService.getAll();
        List<LocationStoreDTO> khoL=lcService.getAll();
        model.addAttribute("productL",productL);
        model.addAttribute("nccL",nccL);
        model.addAttribute("khoL",khoL);
        return "PNK/quanlypnk";
    }
}
