package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Date birthday;
    private String groupCustomer;
    private int point;
    private byte discount;
    private String description;

}
