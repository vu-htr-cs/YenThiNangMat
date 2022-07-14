package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.ComboDTO;
import com.yenthinangmat.manager.entity.ComboEntity;
import com.yenthinangmat.manager.mapper.ComboMapper;
import com.yenthinangmat.manager.repository.ComboRepository;
import com.yenthinangmat.manager.service.ComboService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComboServiceImpl implements ComboService {
    private final ComboRepository comboRepository;

    public ComboServiceImpl(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    @Override
    @Transactional
    public ComboEntity saveE(ComboEntity comboEntity) {
        return comboRepository.save(comboEntity);
    }

    @Override
    public List<ComboDTO> getListCombo(Pageable pageable) {
        List<ComboEntity> comboEntities= comboRepository.findAll(pageable).stream().collect(Collectors.toList());
        List<ComboDTO> res= new ArrayList<>();
        comboEntities.forEach(combo->res.add(ComboMapper.toDTO(combo)));
        return res;
    }

    @Override
    public long count() {
        return comboRepository.count();
    }

    @Override
    public ComboEntity findOneE(Long id) {
        return comboRepository.findFirstById(id);
    }

}
