package com.yenthinangmat.manager.mapper;

import com.yenthinangmat.manager.dto.PNKDTO;
import com.yenthinangmat.manager.dto.PnkAddDTO;
import com.yenthinangmat.manager.entity.LocationStoreEntity;
import com.yenthinangmat.manager.entity.PNKEntity;
import com.yenthinangmat.manager.entity.ProviderEntity;

public class PNKMapper {
    public static PNKDTO toDTO(PNKEntity pnkEntity){
        PNKDTO pnkdto=new PNKDTO();
        pnkdto.setId(pnkEntity.getId());
        pnkdto.setKho(pnkEntity.getKho().getName());
        pnkdto.setNcc(pnkEntity.getNcc().getName());
        pnkdto.setGhiChu(pnkEntity.getGhiChu());
        pnkdto.setNguoiNhan(pnkEntity.getNguoiNhan());
        pnkdto.setNgayGhiSo(pnkEntity.getNgayGhiSo());
        pnkdto.setLoaiNhapKho(pnkEntity.getLoaiNhapKho());
        pnkdto.setSoChungTu(pnkEntity.getSoChungTu());
        pnkdto.setSotien(pnkEntity.getSotien());
        return pnkdto;
    }
    public static PNKEntity toEntity(PnkAddDTO pnkAddDTO, LocationStoreEntity kho, ProviderEntity ncc){
        PNKEntity pnkEntity=new PNKEntity();
        pnkEntity.setId(pnkAddDTO.getId());
        pnkEntity.setKho(kho);
        pnkEntity.setNcc(ncc);
        pnkEntity.setGhiChu(pnkAddDTO.getGhiChu());
        pnkEntity.setNguoiNhan(pnkAddDTO.getNguoiNhan());
        pnkEntity.setNgayGhiSo(pnkAddDTO.getNgayGhiSo());
        pnkEntity.setLoaiNhapKho(pnkAddDTO.getLoaiNhapKho());
        pnkEntity.setSoChungTu(pnkAddDTO.getSoChungTu());
        pnkEntity.setSotien(pnkAddDTO.getSotien());
        return pnkEntity;
    }
}
