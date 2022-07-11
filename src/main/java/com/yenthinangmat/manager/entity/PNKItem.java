package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PNKItem {
    private Long productId; // Truy xuat product va tao key cho map
    private String productName;
    private String unit;//Don vi tinh
    private int giavon;
    private int soluong;
}
