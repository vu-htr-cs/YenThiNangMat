package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.dto.CustomerDTO;
import com.yenthinangmat.manager.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll();
    CustomerDTO add(CustomerDTO customerDTO);
    void deleteOne(Long id);
    CustomerDTO findOne(Long id);
    void updateCustomer(CustomerDTO customerDTO);
    CustomerEntity findOneE(Long id);
    List<CustomerDTO> getByPage(Pageable pageable);
    long count();


}
