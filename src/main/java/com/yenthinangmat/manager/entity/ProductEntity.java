package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="product_name")
    private String product_name;

    @ManyToOne
    @JoinColumn(name="unit_id",nullable = false)
    private UnitEntity unitEntity;

    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private CategoryEntity categoryEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="description_id",referencedColumnName = "id")
    private DescriptionEntity descriptionEntity;

    @Column(name="price")
    private int price;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="modified_by")
    private String modifiedBy;
    @Column(name="modified_at")
    private Timestamp modifiedAt;
    @Column(name="deleted_by")
    private String deletedBy;
    @Column(name="deleted_at")
    private Timestamp deletedAt;

    @OneToMany(mappedBy="product")
    private List<ComboProductEntity> cplist;

    @OneToMany(mappedBy = "productCtp")
    private List<CtpEntity> ctpList;

    @OneToMany(mappedBy = "inventory_pID",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
    private List<InventoryEntity> inventory;
    @OneToMany(mappedBy = "drpID",fetch = FetchType.LAZY)
    private List<DetailReceiptEntity> listDRP;
}
