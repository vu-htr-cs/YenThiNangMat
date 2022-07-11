package com.yenthinangmat.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Component {
    private Long productID;
    private String img;
    private int price;
    private String productName;
    private int qty=1;
}
