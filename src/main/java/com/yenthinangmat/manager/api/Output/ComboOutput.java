package com.yenthinangmat.manager.api.Output;

import com.yenthinangmat.manager.dto.ComboDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComboOutput {
    private int page;
    private int totalPage;
    private List<ComboDTO> list= new ArrayList<>();
}
