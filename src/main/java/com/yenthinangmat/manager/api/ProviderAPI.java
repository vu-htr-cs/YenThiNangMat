package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.dto.ProviderDTO;
import com.yenthinangmat.manager.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderAPI {
    private final ProviderService providerService;

    public ProviderAPI(ProviderService providerService) {
        this.providerService = providerService;
    }
    @GetMapping("/api/employee/provider/get")
    public List<ProviderDTO> getAll(){
        return providerService.getAll();
    }
}
