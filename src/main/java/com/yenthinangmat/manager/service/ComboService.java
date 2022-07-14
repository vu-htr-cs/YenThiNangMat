package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.ComboDTO;
import com.yenthinangmat.manager.entity.ComboEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComboService {
    ComboEntity saveE(ComboEntity comboEntity);
    List<ComboDTO> getListCombo(Pageable pageable);
    long count();
    ComboEntity findOneE(Long id);
}
