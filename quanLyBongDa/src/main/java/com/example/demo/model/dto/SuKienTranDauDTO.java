package com.example.demo.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SuKienTranDauDTO {
    private int id;
    private int idTranDau;
    private int idSuKien;
    // id là id của cầu thủ đá không phải cầu thủ
    private int idCauThuTao;
    private int idCauThuNhan;
    private int idSuKienNguyenNhan;
    private Double thoiGianDienRa;
    private String moTa;
}
