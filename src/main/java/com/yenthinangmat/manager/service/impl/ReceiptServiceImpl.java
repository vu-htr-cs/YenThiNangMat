package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.request.InvoiceRequest;
import com.yenthinangmat.manager.entity.ReceiptEntity;
import com.yenthinangmat.manager.repository.ReceiptRepository;
import com.yenthinangmat.manager.service.CustomerService;
import com.yenthinangmat.manager.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    final
    CustomerService customerService;

    public ReceiptServiceImpl(CustomerService customerService, ReceiptRepository receiptRepository) {
        this.customerService = customerService;
        this.receiptRepository = receiptRepository;
    }
    final
    ReceiptRepository receiptRepository;

    @Override
    public ReceiptEntity saveE(InvoiceRequest invoiceRequest, int ck, int subtotal) {
        ReceiptEntity receiptEntity=new ReceiptEntity();
        receiptEntity.setCk(ck);
        receiptEntity.setTongCong(subtotal);
        receiptEntity.setShd(invoiceRequest.getSoHoaDon());
        receiptEntity.setListDetail(new ArrayList<>());
        if(invoiceRequest.getCustomerId()!=null) {
            receiptEntity.setCustomerEntity(customerService.findOneE(invoiceRequest.getCustomerId()));
        }
        else receiptEntity.setCustomerEntity(customerService.findOneE(5L));
        LocalDate localDate= LocalDate.now();
        receiptEntity.setNgayLap(Date.valueOf(localDate));
        // created by, id
        return receiptRepository.save(receiptEntity);// tra ve co Id


    }

    @Override
    public ReceiptEntity save(ReceiptEntity receiptEntity) {
        return receiptRepository.save(receiptEntity);
    }
}
