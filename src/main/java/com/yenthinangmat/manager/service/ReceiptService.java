package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.request.InvoiceRequest;
import com.yenthinangmat.manager.entity.ReceiptEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReceiptService {
    ReceiptEntity saveE(InvoiceRequest invoiceRequest,int ck,int subtotal);
    ReceiptEntity save(ReceiptEntity receiptEntity);
}
