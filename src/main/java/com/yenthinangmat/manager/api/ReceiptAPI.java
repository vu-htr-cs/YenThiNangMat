package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.ReceiptOutput;
import com.yenthinangmat.manager.dto.ReceiptDTO;
import com.yenthinangmat.manager.service.ReceiptService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ReceiptAPI {
    private final ReceiptService receiptService;

    public ReceiptAPI(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/api/admin/receipt/{page}")
    public ReceiptOutput listReceipt(@PathVariable(name = "page") int page,
                                     @RequestParam(name = "start") String start, @RequestParam(name = "end", required = false, defaultValue = "1970-01-01") String end) {
        ReceiptOutput receiptOutput = new ReceiptOutput();
        receiptOutput.setPage(page);
        int offset = (page - 1) * 9;
        List<ReceiptDTO> res = new ArrayList<>();
        List<ReceiptDTO> temp;
        temp = receiptService.
                getAll(new Timestamp(Date.valueOf(start).getTime()), new Timestamp(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)).getTime()));
        for (int i = offset; i < Math.min(offset + 9, temp.size()); i++) {
            res.add(temp.get(i));
        }
        receiptOutput.setListReceipt(res);
        receiptOutput.setTotalPage((int) Math.ceil((double) temp.size() / 9));

        return receiptOutput;
    }

    @DeleteMapping("/api/admin/receipt/delete")
    public ResponseEntity<?> deleteReceipt(@RequestParam(name = "listId") Long[] listId) {
        for (Long id : listId) {
            receiptService.deleteOne(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
