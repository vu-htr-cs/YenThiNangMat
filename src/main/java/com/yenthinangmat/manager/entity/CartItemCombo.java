package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemCombo {
    private Long comboId;
    private String comboName;
    private int qty;
    private int price;
    private byte discount;
}
