package com.javatools.crud.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TranDau {
    int id;
    Timestamp thoiGianBatDau;
    String sanBong;
    int vongDau;
    String trongTai;
    String doiNha;
    String doiKhach;
    String trangThai;
    int soNguoiDenSan;
    int tongSoBanThang;
    int banThangDoiNha;
    int banThangDoiKhach;
    String thoiGianGhiBanDoiNha;
    String thoiGianGhiBanDoiKhach;
}
