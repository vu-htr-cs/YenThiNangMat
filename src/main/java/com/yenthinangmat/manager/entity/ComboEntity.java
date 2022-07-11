package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "combo")
public class ComboEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="price")
    private int price;
    @Column(name="name")
    private String comboName;
    @OneToMany(mappedBy = "combo")
    private List<ComboProductEntity> cplist;
}
