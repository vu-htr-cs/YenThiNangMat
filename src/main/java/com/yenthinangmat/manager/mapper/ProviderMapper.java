package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.ProviderDTO;
import com.yenthinangmat.manager.entity.ProviderEntity;

public class ProviderMapper {
    public static ProviderDTO toDTO(ProviderEntity providerEntity){
        ProviderDTO providerDTO=new ProviderDTO();
        providerDTO.setId(providerEntity.getId());
        providerDTO.setName(providerEntity.getName());
        providerDTO.setMota(providerEntity.getMota());
        return providerDTO;
    }
    public static ProviderEntity toEntity(ProviderDTO providerDTO){
        ProviderEntity provider=new ProviderEntity();
        provider.setId(providerDTO.getId());
        provider.setName(providerDTO.getName());
        provider.setMota(providerDTO.getMota());
        return provider;
    }
}
