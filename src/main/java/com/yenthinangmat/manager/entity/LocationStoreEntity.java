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
@Table(name="locationstore")
public class LocationStoreEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name="code")
    private String code;
    @Column(name="mota")
    private String mota;
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

    @OneToMany(mappedBy = "kho")
    private List<PNKEntity> pnkList;
}
