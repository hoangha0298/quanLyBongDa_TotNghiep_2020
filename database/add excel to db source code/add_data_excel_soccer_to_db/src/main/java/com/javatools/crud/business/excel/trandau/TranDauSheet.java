package com.javatools.crud.business.excel.trandau;

import com.javatools.crud.business.excel.SheetToObject;
import com.javatools.crud.model.TranDau;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class TranDauSheet extends SheetToObject<TranDau> {

    // mapping cột excel vào thuộc tính đối tượng, giá trị biến là vị trí cột trái sang phải bắt đầu từ 0, cột = -1 là không dùng tới

    public static int id = -1;
    public static int thoiGianBatDau = columnToInt('a');
    public static int sanBong = 63;
    public static int vongDau = columnToInt('h');
    public static int trongTai = columnToInt('g');
    public static int doiNha = columnToInt('e');
    public static int doiKhach = columnToInt('f');
    public static int trangThai = columnToInt('c');
    public static int soNguoiDenSan = columnToInt('d');
    public static int tongSoBanThang = columnToInt('o');
    public static int banThangDoiNha = columnToInt('m');
    public static int banThangDoiKhach = columnToInt('n');
    public static int thoiGianGhiBanDoiNha = columnToInt('s');
    public static int thoiGianGhiBanDoiKhach = columnToInt('t');

    public static int countNextRow = 1;

    public TranDauSheet(MultipartFile fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    public TranDauSheet(InputStream fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    private int idTranDau = 1;
    @Override
    protected TranDau doWithRow(Row row) {
        TranDau tranDau = new TranDau();

        tranDau.setId(idTranDau++);
        tranDau.setThoiGianBatDau(new Timestamp(Long.parseLong(getStringCellValue(row, thoiGianBatDau)) * 1000));

        String strSanBong = getStringCellValue(row, sanBong);
        // đổi tên sân Wembley Stadium (London)
        if (strSanBong.equals("Wembley Stadium (London)")) strSanBong = "Tottenham Hotspur Stadium (London)";
        tranDau.setSanBong(strSanBong);
        tranDau.setVongDau(Integer.valueOf(getStringCellValue(row, vongDau)));
        tranDau.setTrongTai(getStringCellValue(row, trongTai));
        tranDau.setDoiNha(getStringCellValue(row, doiNha));
        tranDau.setDoiKhach(getStringCellValue(row, doiKhach));
        tranDau.setTrangThai(getStringCellValue(row, trangThai));
        tranDau.setSoNguoiDenSan(Integer.parseInt(getStringCellValue(row, soNguoiDenSan)));
        tranDau.setTongSoBanThang(Integer.parseInt(getStringCellValue(row, tongSoBanThang)));
        tranDau.setBanThangDoiNha(Integer.parseInt(getStringCellValue(row, banThangDoiNha)));
        tranDau.setBanThangDoiKhach(Integer.parseInt(getStringCellValue(row, banThangDoiKhach)));
        tranDau.setThoiGianGhiBanDoiNha(getStringCellValue(row, thoiGianGhiBanDoiNha));
        tranDau.setThoiGianGhiBanDoiKhach(getStringCellValue(row, thoiGianGhiBanDoiKhach));

        datas.add(tranDau);
        return tranDau;
    }

}
