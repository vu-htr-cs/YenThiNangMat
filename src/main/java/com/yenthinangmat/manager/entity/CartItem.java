package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private Long productId;
    private String productName;
    private int qty;
    private int price;
    private byte discount;
}
