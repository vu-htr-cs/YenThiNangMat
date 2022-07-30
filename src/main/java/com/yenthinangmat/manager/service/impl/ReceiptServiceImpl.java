package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.ReceiptDTO;
import com.yenthinangmat.manager.dto.request.InvoiceRequest;
import com.yenthinangmat.manager.entity.DetailReceiptEntity;
import com.yenthinangmat.manager.entity.ReceiptEntity;
import com.yenthinangmat.manager.mapper.ReceiptMapper;
import com.yenthinangmat.manager.repository.ReceiptRepository;
import com.yenthinangmat.manager.service.CustomerService;
import com.yenthinangmat.manager.service.DetailReceiptService;
import com.yenthinangmat.manager.service.ReceiptService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    final
    DetailReceiptService detailReceiptService;

    final
    CustomerService customerService;

    public ReceiptServiceImpl(CustomerService customerService, ReceiptRepository receiptRepository, DetailReceiptService detailReceiptService) {
        this.customerService = customerService;
        this.receiptRepository = receiptRepository;
        this.detailReceiptService = detailReceiptService;
    }
    final
    ReceiptRepository receiptRepository;

    @Override
    @Transactional
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
//        LocalDate localDate= LocalDate.now();
//        receiptEntity.setNgayLap(Date.valueOf(localDate));
        receiptEntity.setNgayLap(Timestamp.from(Instant.now()));
        // created by, id
        return receiptRepository.save(receiptEntity);// tra ve co Id
    }

    @Override
    @Transactional
    public ReceiptEntity save(ReceiptEntity receiptEntity) {
        return receiptRepository.save(receiptEntity);
    }

    @Override
    public List<ReceiptDTO> getAll(Pageable pageable) {
       return receiptRepository.findAll(pageable).stream().map(ReceiptMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDTO> getAll(Timestamp start, Timestamp end) {
        return receiptRepository.getAllFromStarToEnd(start,end).stream().map(ReceiptMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return receiptRepository.count();
    }

    @Override
    public void deleteOne(Long id) {
        receiptRepository.deleteById(id);
    }
    @Override
    public List<String> getDetail(Long id){
        List<DetailReceiptEntity> temp=detailReceiptService.getAllByReceiptID(id);
        return temp.stream().map(item->
             item.getProductName()+" x"+item.getQty() +", CK:"+item.getCk()+"%").collect(Collectors.toList());
    }
}
