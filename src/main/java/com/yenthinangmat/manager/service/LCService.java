package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.LocationStoreDTO;

import java.util.List;

public interface LCService {
    List<LocationStoreDTO> getAll();

    LocationStoreDTO add(LocationStoreDTO locationStoreDTO);

    void deleteOne(Long id);

    LocationStoreDTO findOne(Long id);

    void update(LocationStoreDTO locationStoreDTO);
}
