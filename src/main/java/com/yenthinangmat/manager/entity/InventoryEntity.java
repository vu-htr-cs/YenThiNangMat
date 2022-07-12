package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    @JoinColumn(name="product_id",nullable = false)
    private ProductEntity inventory_pID;
    private int soluong;
    private int giavon;
}
