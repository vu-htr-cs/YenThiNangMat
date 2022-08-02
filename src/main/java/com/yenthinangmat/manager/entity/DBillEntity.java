package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name="dbill")
public class DBillEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="receiptId")
    private ReceiptEntity rDb;

    @Column(name="productId")
    private Long productId;
    @Column(name="comboId")
    private Long comboId;
    @Column(name="qty")
    private int qty;

}
