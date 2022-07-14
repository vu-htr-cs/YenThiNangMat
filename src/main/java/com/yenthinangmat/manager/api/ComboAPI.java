package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.ComboOutput;
import com.yenthinangmat.manager.service.ComboService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComboAPI {
    private final ComboService comboService;

    public ComboAPI(ComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping("/api/productsell/combo/{page}")
    public ComboOutput getComboSell(@PathVariable(name="page") int page){
        ComboOutput comboOutput=new ComboOutput();
        comboOutput.setPage(page);
        comboOutput.setList(comboService.getListCombo(PageRequest.of(page-1,9)));
        comboOutput.setTotalPage((int)Math.ceil((double)comboService.count()/9));System.out.println(comboService.count());
        return comboOutput;
    }
}
