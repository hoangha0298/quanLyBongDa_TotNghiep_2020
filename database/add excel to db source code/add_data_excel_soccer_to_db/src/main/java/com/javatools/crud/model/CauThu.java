package com.javatools.crud.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CauThu {
    int id;
    String hoVaTen;
    Timestamp ngaySinh;
    int tuoi;
    String viTri;
    String doiBong;
    String quocTich;
    int tongSoBan;
    int soBanSanNha;
    int soBanSanKhach;
    int tongHoTro;
    int hoTroSanNha;
    int hoTroSanKhach;
}
