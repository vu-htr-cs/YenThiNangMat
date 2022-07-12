package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.LocationStoreDTO;
import com.yenthinangmat.manager.entity.LocationStoreEntity;
import com.yenthinangmat.manager.mapper.LCMapper;
import com.yenthinangmat.manager.repository.LocationStoreRepository;
import com.yenthinangmat.manager.service.LCService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class LCServiceImpl implements LCService {

    private final LocationStoreRepository locationStoreRepository;

    public LCServiceImpl(LocationStoreRepository locationStoreRepository) {
        this.locationStoreRepository = locationStoreRepository;
    }

    @Override
    public List<LocationStoreDTO> getAll() {
        List<LocationStoreDTO> res = new ArrayList<>();
        List<LocationStoreEntity> truyvan = locationStoreRepository.findAll();
        truyvan.forEach((item) -> res.add(LCMapper.toDTO(item)));
        return res;
    }

    @Override
    @Transactional
    public LocationStoreDTO add(LocationStoreDTO locationStoreDTO) {
        LocationStoreEntity locationStoreEntity = LCMapper.toEntity(locationStoreDTO);
        locationStoreEntity.setCreatedAt(Timestamp.from(Instant.now()));
        LocationStoreEntity le = locationStoreRepository.save(locationStoreEntity);
        LocationStoreDTO res = LCMapper.toDTO(le);
        return res;
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        locationStoreRepository.deleteById(id);
    }

    @Override
    public LocationStoreDTO findOne(Long id) {
        LocationStoreEntity le = locationStoreRepository.findFirstById(id);
        return LCMapper.toDTO(le);
    }

    @Override
    @Transactional
    public void update(LocationStoreDTO locationStoreDTO) {
        LocationStoreEntity locationStoreEntity = LCMapper.toEntity(locationStoreDTO);
        locationStoreEntity.setModifiedAt(Timestamp.from(Instant.now()));
        locationStoreRepository.save(locationStoreEntity);
    }

    @Override
    public LocationStoreEntity findOneE(Long id) {
        return locationStoreRepository.findFirstById(id);
    }
}
