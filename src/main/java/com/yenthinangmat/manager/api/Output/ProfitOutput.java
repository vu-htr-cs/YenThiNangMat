package com.yenthinangmat.manager.api.Output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfitOutput {
    private Long productId;
    private Long qty;
    private Long tongnhap;
    private String name;
    private String dvt;

    private int slBan;
    private int tienBan;


}
