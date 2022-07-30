package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.service.PNKService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class    PNKAPI {
    private final PNKService pnkService;
    public PNKAPI(PNKService pnkService) {
        this.pnkService = pnkService;
    }
    @GetMapping("/api/employee/pnk/show")
    public List<PNKDTO> showList(){
        return pnkService.getAll();
    }
}
