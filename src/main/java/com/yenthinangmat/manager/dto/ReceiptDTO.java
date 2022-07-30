package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReceiptDTO {
    private Long id;
    private String shd;
    private Timestamp ngay;
    private String khachHang;
    private int tienHang;
    private int giamGia;
}
