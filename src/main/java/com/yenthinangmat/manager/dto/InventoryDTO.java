package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {
    private Long id;
    private String productName;
    private String unit;
    private int soluong;
    private int tienTonKho;
}
