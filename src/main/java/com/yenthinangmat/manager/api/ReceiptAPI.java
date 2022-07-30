package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.ReceiptOutput;
import com.yenthinangmat.manager.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptAPI {
    @Autowired
    private ReceiptService receiptService;
    @GetMapping("/admin/receipt/{page}")
    public ReceiptOutput listReceipt(@PathVariable(name="page") int page){
        ReceiptOutput receiptOutput=new ReceiptOutput();
        receiptOutput.setPage(page);
        receiptOutput.setListReceipt(receiptService.getAll(PageRequest.of(page-1,9, Sort.by("ngayLap").descending())));
        return receiptOutput;
    }
}
