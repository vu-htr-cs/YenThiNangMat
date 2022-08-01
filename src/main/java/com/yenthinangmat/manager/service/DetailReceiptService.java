package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.DetailReceiptEntity;

import java.sql.Timestamp;
import java.util.List;

public interface DetailReceiptService {
    List<DetailReceiptEntity> getAllByReceiptID(Long id);
    List<Object[]> getAllProductX(Timestamp start, Timestamp end);
    List<DetailReceiptEntity> getAllProductProfit(Timestamp start, Timestamp end);
}
