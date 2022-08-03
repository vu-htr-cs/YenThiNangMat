package com.yenthinangmat.manager.controller;

import com.yenthinangmat.manager.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReceiptController {
    final
    ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/employee/receipt")
    public String listReceipt(){
        return "Receipt/quanlyhoadon";
    }
    @GetMapping("/admin/receipt/{id}/edit")
    public String getDetail(@PathVariable(name="id")Long id, Model model, @RequestParam("shd")String shd){
         List<String> res= receiptService.getDetail(id);
         model.addAttribute("shd",shd);
         model.addAttribute("res",res);
         return "Receipt/chitiethoadon";
    }
}
