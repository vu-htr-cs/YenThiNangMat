package com.yenthinangmat.manager.api.Output;

import com.yenthinangmat.manager.dto.ProductDisplayDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductOutput {
    private int page;
    private int totalPage;
    private List<ProductDisplayDTO> list= new ArrayList<>();
}
