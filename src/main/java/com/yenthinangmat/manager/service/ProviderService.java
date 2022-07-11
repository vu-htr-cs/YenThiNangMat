package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.ProviderDTO;
import com.yenthinangmat.manager.entity.ProviderEntity;

import java.util.List;

public interface ProviderService {
    ProviderEntity saveE(ProviderEntity providerEntity);
    List<ProviderDTO> getAll();
}
