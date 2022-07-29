package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="receipt")
public class ReceiptEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="shd")
    private String shd;
    @Column(name="ngaylap")
    private Date ngayLap;
    @Column(name="tongcong")
    private int tongCong;
    @Column(name="ck")
    private int ck;
    @Column(name="created_by")
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "customeid",nullable = false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "receiptEntity",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DetailReceiptEntity> listDetail;


}
