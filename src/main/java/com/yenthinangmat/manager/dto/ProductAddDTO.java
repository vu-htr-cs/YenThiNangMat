package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddDTO {
    private Long id;
    private String name;
    private Long unitId;
    private Long categoryId;
    private int price;
    private String content;
    private String img;
}
