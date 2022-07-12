package com.yenthinangmat.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class PnkAddDTO {
    private Long id;//null

    private Long khoID;

    private Long nccID;

    private String ghiChu;

    private String nguoiNhan;

    private Date ngayGhiSo;

    private String loaiNhapKho;

    private String soChungTu;//Tao bang logic

    private int sotien;//Tinh bang hashmap
}
