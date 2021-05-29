package com.javatools.crud.model;

import lombok.Data;

@Data
public class DoiBong {
    int id;
    String ten;
    String tenDayDu;
    String quocTich;
    Long muaGiai;
    int soTranDaDa;
    int soTranSanNha;
    int soTranSanKhach;
    int soTranThang;
    int soTranThangSanNha;
    int soTranThangSanKhach;
    String sanBong;
}
