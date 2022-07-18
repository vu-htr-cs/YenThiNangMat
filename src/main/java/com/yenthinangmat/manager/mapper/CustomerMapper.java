package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.CustomerDTO;
import com.yenthinangmat.manager.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerDTO toDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setBirthday(customerEntity.getBirthday());
        customerDTO.setGroupCustomer(customerEntity.getGroupCustomer());
        customerDTO.setPoint(customerEntity.getPoint());
        customerDTO.setDiscount(customerEntity.getDiscount());
        customerDTO.setDescription(customerEntity.getDescription());
        return customerDTO;
    }
    public static CustomerEntity toEntity(CustomerDTO customerDTO){
        CustomerEntity customer=new CustomerEntity();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setGroupCustomer(customerDTO.getGroupCustomer());
        customer.setPoint(customerDTO.getPoint());
        customer.setDiscount(customerDTO.getDiscount());
        customer.setDescription(customerDTO.getDescription());
        return customer;
    }
}
