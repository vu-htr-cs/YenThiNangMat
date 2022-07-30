package com.yenthinangmat.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReceiptController {
    @GetMapping("/admin/receipt")
    public String listReceipt(){
        return "Receipt/quanlyhoadon";
    }
}
