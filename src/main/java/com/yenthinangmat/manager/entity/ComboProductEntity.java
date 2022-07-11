package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name="comboproduct")
public class ComboProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="combo_id")
    private ComboEntity combo;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    private int qty;

}
