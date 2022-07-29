package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
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

    public DetailReceiptEntity() {
    }

    public DetailReceiptEntity(String productName, int qty, ReceiptEntity receiptEntity) {
        this.productName = productName;
        this.qty = qty;
        this.receiptEntity = receiptEntity;
    }

    @ManyToOne
    @JoinColumn(name = "receiptid",nullable = false)
    private ReceiptEntity receiptEntity;


}
