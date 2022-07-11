package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name="ctp")
public class CtpEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="pnkid")
    private PNKEntity pnk;
    @ManyToOne
    @JoinColumn(name="productid")
    private ProductEntity productCtp;
    @Column(name="soluong")
    private int soluong;
    @Column(name="giavon")
    private int giaVon;
}
