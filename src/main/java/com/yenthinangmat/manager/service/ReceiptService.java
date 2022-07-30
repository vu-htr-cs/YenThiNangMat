package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.ReceiptDTO;
import com.yenthinangmat.manager.dto.request.InvoiceRequest;
import com.yenthinangmat.manager.entity.ReceiptEntity;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;


public interface ReceiptService extends BaseService {
    ReceiptEntity saveE(InvoiceRequest invoiceRequest,int ck,int subtotal);
    ReceiptEntity save(ReceiptEntity receiptEntity);
    List<ReceiptDTO> getAll(Pageable pageable);
    List<ReceiptDTO> getAll(Timestamp start,Timestamp end);
    List<String> getDetail(Long id);

}
