package com.yenthinangmat.manager.api.Output;

import com.yenthinangmat.manager.dto.ReceiptDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReceiptOutput {
    private int page;
    private int totalPage;
    private List<ReceiptDTO> listReceipt=new ArrayList<>();
}
