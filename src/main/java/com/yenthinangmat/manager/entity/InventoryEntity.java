package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name="inventory")
public class InventoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)//Khong duoc la unique
    private ProductEntity inventory_pID;
    @Column(name="soluong")
    private int soluong;
    @Column(name="giavon")
    private int giavon;
    
}
