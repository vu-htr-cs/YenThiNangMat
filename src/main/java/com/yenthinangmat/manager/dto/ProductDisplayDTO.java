package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDisplayDTO {
    private Long id;
    private String productName;
    private String unitName;
    private String categoryName;
    private String content;
    private String img;
    private int price;
}
