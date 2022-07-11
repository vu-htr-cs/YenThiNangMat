package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class PNKDTO {
    private Long id;

    private String kho;

    private String ncc;

    private String ghiChu;

    private String nguoiNhan;

    private Timestamp ngayGhiSo;

    private String loaiNhapKho;

    private String soChungTu;

    private int sotien;

}
