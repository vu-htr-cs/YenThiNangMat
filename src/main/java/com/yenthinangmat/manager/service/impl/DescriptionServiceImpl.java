package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.entity.DescriptionEntity;
import com.yenthinangmat.manager.repository.DescriptionRepository;
import com.yenthinangmat.manager.service.DescriptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Override
    @Transactional
    public DescriptionEntity insertAndReturnEntity(DescriptionEntity descriptionEntity) {
        return descriptionRepository.save(descriptionEntity);
    }

    @Override
    public DescriptionEntity findOne(Long id) {
        return descriptionRepository.findFirstById(id);
    }
}
