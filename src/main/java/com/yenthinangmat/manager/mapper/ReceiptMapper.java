package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.ReceiptDTO;
import com.yenthinangmat.manager.entity.ReceiptEntity;

public class ReceiptMapper {
    public static ReceiptDTO toDTO(ReceiptEntity receiptEntity){
        ReceiptDTO receiptDTO=new ReceiptDTO();
        receiptDTO.setId(receiptEntity.getId());
        receiptDTO.setShd(receiptEntity.getShd());
        receiptDTO.setNgay(receiptEntity.getNgayLap());
        receiptDTO.setKhachHang(receiptEntity.getCustomerEntity().getName());
        receiptDTO.setTienHang(receiptDTO.getTienHang());
        receiptDTO.setGiamGia(receiptDTO.getGiamGia());
        return receiptDTO;
    }
}
