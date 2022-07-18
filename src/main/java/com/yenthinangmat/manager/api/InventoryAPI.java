package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.InventoryOutput;
import com.yenthinangmat.manager.dto.InventoryDTO;
import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class InventoryAPI {
    final
    InventoryService inventoryService;

    public InventoryAPI(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping("/api/inventory/{page}")
    public InventoryOutput listInventory(@PathVariable(name="page")int page){
        InventoryOutput inventoryOutput=new InventoryOutput();
        inventoryOutput.setPage(page);
        List<InventoryDTO> temp=inventoryService.getByPage().stream().toList();
        int offset=(page-1)*9;
        List<InventoryDTO> res=new ArrayList<>();
        for(int i=offset;i<Math.min(offset+9,temp.size());i++){
            res.add(temp.get(i));
        }
        inventoryOutput.setList(res);
        inventoryOutput.setTotalPage((int)Math.ceil((double)res.size()/9));
        return inventoryOutput;
    }
}
