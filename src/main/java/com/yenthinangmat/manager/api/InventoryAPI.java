package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.api.Output.InventoryOutput;
import com.yenthinangmat.manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        inventoryOutput.setList(inventoryService.getByPage(PageRequest.of(page-1,9, Sort.by("id").descending())));
        // CAN XU LY LAI LOGIC, GROUP BY SAN PHAM VA TINH TONG TIEN VI MOI SP CO GIA VON KHAC NHAU
        inventoryOutput.setTotalPage((int)Math.ceil((double)inventoryService.count()/9));
        return inventoryOutput;
    }
}
