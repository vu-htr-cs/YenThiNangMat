package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.entity.PNKEntity;
import com.yenthinangmat.manager.mapper.PNKMapper;
import com.yenthinangmat.manager.repository.PNKRepository;
import com.yenthinangmat.manager.service.PNKService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PNKServiceImpl implements PNKService {
    private final PNKRepository pnkRepository;

    public PNKServiceImpl(PNKRepository pnkRepository) {
        this.pnkRepository = pnkRepository;
    }

    @Override
    public PNKEntity save(PNKEntity pnk) {
        return pnkRepository.save(pnk);
    }

    @Override
    public List<PNKDTO> getAll() {
        return pnkRepository.findAll().stream().map(PNKMapper::toDTO).collect(Collectors.toList());
    }
}
