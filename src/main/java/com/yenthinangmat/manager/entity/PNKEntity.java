package com.yenthinangmat.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="pnk")
public class PNKEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="khoid",nullable = false)
    private LocationStoreEntity kho;

    @ManyToOne
    @JoinColumn(name="nccid")
    private ProviderEntity ncc;

    @Column(name="ghichu")
    private String ghiChu;
    @Column(name="nguoinhan")
    private String nguoiNhan;

    @Column(name="ngayghiso")
    private Date ngayGhiSo;

    @Column(name="loainhapkho")
    private String loaiNhapKho;

    @Column(name ="sochungtu")
    private String soChungTu;
    @Column(name="sotien")
    private int sotien;

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

    @OneToMany(mappedBy = "pnk",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})//mac dinh @onetomany lazy
    private List<CtpEntity> ctpList;
}
