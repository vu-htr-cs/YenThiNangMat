package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.api.Output.ReceiptOutput;
import com.yenthinangmat.manager.dto.ReceiptDTO;
import com.yenthinangmat.manager.dto.request.InvoiceRequest;
import com.yenthinangmat.manager.entity.ReceiptEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptService {
    ReceiptEntity saveE(InvoiceRequest invoiceRequest,int ck,int subtotal);
    ReceiptEntity save(ReceiptEntity receiptEntity);
    List<ReceiptDTO> getAll(Pageable pageable);
}
