package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.DescriptionEntity;

public interface DescriptionService {
    DescriptionEntity insertAndReturnEntity(DescriptionEntity descriptionEntity);
    DescriptionEntity findOne(Long id);
}
