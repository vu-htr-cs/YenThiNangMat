package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class PnkAddDTO {
    private Long id;

    private Long khoID;

    private Long nccID;

    private String ghiChu;

    private String nguoiNhan;

    private Timestamp ngayGhiSo;

    private String loaiNhapKho;

    private String soChungTu;

    private int sotien;
}
