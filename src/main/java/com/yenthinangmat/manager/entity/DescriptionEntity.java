package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name="description")
public class DescriptionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name="img")
    private String img;

    @OneToOne(mappedBy = "descriptionEntity")
    private ProductEntity productEntity;
}
