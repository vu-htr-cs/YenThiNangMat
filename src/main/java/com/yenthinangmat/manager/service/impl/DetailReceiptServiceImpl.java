package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;
import com.yenthinangmat.manager.repository.DetailReceiptRepository;
import com.yenthinangmat.manager.service.DetailReceiptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailReceiptServiceImpl implements DetailReceiptService {
    private final DetailReceiptRepository detailReceiptRepository;

    public DetailReceiptServiceImpl(DetailReceiptRepository detailReceiptRepository) {
        this.detailReceiptRepository = detailReceiptRepository;
    }

    @Override
    public List<DetailReceiptEntity>getAllByReceiptID(Long id) {
        return detailReceiptRepository.findAllByRID(id);
    }
}
