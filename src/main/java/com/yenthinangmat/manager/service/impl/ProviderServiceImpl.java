package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.ProviderDTO;
import com.yenthinangmat.manager.entity.ProviderEntity;
import com.yenthinangmat.manager.mapper.ProviderMapper;
import com.yenthinangmat.manager.repository.ProviderRepository;
import com.yenthinangmat.manager.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public ProviderEntity saveE(ProviderEntity providerEntity) {
        return providerRepository.save(providerEntity);
    }

    @Override
    public List<ProviderDTO> getAll() {
        return providerRepository.findAll().stream().map(item-> ProviderMapper.toDTO(item)).collect(Collectors.toList());
    }

    @Override
    public ProviderEntity findOneE(Long id) {
        return providerRepository.findFirstById(id);
    }
}
