package com.yenthinangmat.manager.api.Output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XNTOutput {
    private Long productId;
    private Long qty;
    private Long tongnhap;
    private Long slXuat=0L;
    private String name;
    private String dvt;
}
