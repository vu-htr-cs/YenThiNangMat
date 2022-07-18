package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="customer")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="birthday")
    private Date birthday;
    @Column(name="groupCustomer")
    private String groupCustomer;
    @Column(name="point")
    private int point;
    @Column(name="discount")
    private byte discount;
    @Column(name="description")
    private String description;
}
