package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.CustomerDTO;
import com.yenthinangmat.manager.entity.CustomerEntity;
import com.yenthinangmat.manager.mapper.CustomerMapper;
import com.yenthinangmat.manager.repository.CustomerRepository;
import com.yenthinangmat.manager.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    final
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> res=new ArrayList<>();
        customerRepository.findAll().forEach(item->res.add(CustomerMapper.toDTO(item)));//getContent
        return res;
    }

    @Override
    @Transactional
    public CustomerDTO add(CustomerDTO customerDTO) {
        CustomerEntity customer=CustomerMapper.toEntity(customerDTO);
        customer=customerRepository.save(customer);
        return CustomerMapper.toDTO(customer);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO findOne(Long id) {
        return CustomerMapper.toDTO(customerRepository.findFirstById(id));
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(CustomerMapper.toEntity(customerDTO));
    }

    @Override
    @Transactional
    public CustomerEntity findOneE(Long id) {
        return customerRepository.findFirstById(id);
    }

    @Override
    public List<CustomerDTO> getByPage(Pageable pageable) {
        List<CustomerDTO> res=new ArrayList<>();
        customerRepository.findAll(pageable).getContent().forEach(item->res.add(CustomerMapper.toDTO(item)));
        return res;
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

}
