package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="detalreceipt")
public class DetailReceiptEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="productname")
    private String productName;
    @Column(name="qty")
    private int qty;

    @Column(name="ck")
    private byte ck;

    @ManyToOne
    @JoinColumn(name = "receiptid",nullable = false)
    private ReceiptEntity receiptEntity;

    @ManyToOne
    @JoinColumn(name="productid",nullable = false)//Khong duoc la unique
    private ProductEntity drpID;

    @Column(name="create_at")
    private Timestamp createAt;


}
