package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;

import java.util.List;

public interface DetailReceiptService {
    List<DetailReceiptEntity> getAllByReceiptID(Long id);
}
