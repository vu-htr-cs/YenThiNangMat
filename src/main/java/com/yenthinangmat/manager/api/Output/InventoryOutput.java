package com.yenthinangmat.manager.api.Output;

import com.yenthinangmat.manager.dto.InventoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class InventoryOutput {
    private int page;
    private int totalPage;
    private Collection<InventoryDTO> list;
}
