package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.UnitDTO;
import com.yenthinangmat.manager.entity.UnitEntity;
import com.yenthinangmat.manager.mapper.UnitMapper;
import com.yenthinangmat.manager.repository.UnitRepository;
import com.yenthinangmat.manager.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<UnitDTO> getAll() {
        List<UnitDTO> res=new ArrayList<>();
        List<UnitEntity> temp = unitRepository.findAll();
        temp.forEach(item->res.add(UnitMapper.toDTO(item)));
        return res;
    }

    @Override
    @Transactional
    public UnitDTO add(UnitDTO unitDTO) {
        UnitEntity unitEntity=UnitMapper.toEntity(unitDTO);
        unitEntity.setCreatedAt(Timestamp.from(Instant.now()));
        UnitEntity ue=unitRepository.save(unitEntity);
        return UnitMapper.toDTO(ue);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        unitRepository.deleteById(id);
    }

    @Override
    public UnitDTO findOne(Long id) {
        UnitEntity ue=unitRepository.findFirstById(id);
        return UnitMapper.toDTO(ue);
    }

    @Override
    @Transactional
    public void update(UnitDTO unitDTO) {
        UnitEntity unitEntity=UnitMapper.toEntity(unitDTO);
        unitEntity.setModifiedAt(Timestamp.from(Instant.now()));
        unitRepository.save(unitEntity);
    }

    @Override
    public UnitEntity findOneE(Long id) {
        return unitRepository.findFirstById(id);
    }
}
