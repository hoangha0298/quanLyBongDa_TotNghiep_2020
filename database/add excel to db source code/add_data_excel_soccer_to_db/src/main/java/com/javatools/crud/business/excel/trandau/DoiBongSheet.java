package com.javatools.crud.business.excel.trandau;

import com.javatools.crud.business.excel.SheetToObject;
import com.javatools.crud.model.DoiBong;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class DoiBongSheet extends SheetToObject<DoiBong> {
    // mapping cột excel vào thuộc tính đối tượng, giá trị biến là vị trí cột trái sang phải bắt đầu từ 0, cột = -1 là không dùng tới
    public static int id = -1;
    public static int ten = columnToInt('b');
    public static int tenDayDu = columnToInt('a');
    public static int quocTich = columnToInt('d');
    public static int muaGiai = columnToInt('c');
    public static int soTranDaDa = columnToInt('e');
    public static int soTranSanNha = columnToInt('f');
    public static int soTranSanKhach = columnToInt('g');
    public static int soTranThang = columnToInt('i');
    public static int soTranThangSanNha = columnToInt('j');
    public static int soTranThangSanKhach = columnToInt('k');

    public static int countNextRow = 1;

    public DoiBongSheet(MultipartFile fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    public DoiBongSheet(InputStream fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    private int idDoiBong = 1;
    @Override
    protected DoiBong doWithRow(Row row) {
        DoiBong doiBong = new DoiBong();

        doiBong.setId(idDoiBong++);
        doiBong.setTen(getStringCellValue(row, ten));
        doiBong.setTenDayDu(getStringCellValue(row, tenDayDu));
        doiBong.setQuocTich(getStringCellValue(row, quocTich));
        doiBong.setSoTranDaDa(Integer.valueOf(getStringCellValue(row, soTranDaDa)));
        doiBong.setSoTranSanNha(Integer.valueOf(getStringCellValue(row, soTranSanNha)));
        doiBong.setSoTranSanKhach(Integer.valueOf(getStringCellValue(row, soTranSanKhach)));
        doiBong.setSoTranThang(Integer.valueOf(getStringCellValue(row, soTranThang)));
        doiBong.setSoTranThangSanNha(Integer.valueOf(getStringCellValue(row, soTranThangSanNha)));
        doiBong.setSoTranThangSanKhach(Integer.valueOf(getStringCellValue(row, soTranThangSanKhach)));

        datas.add(doiBong);
        return doiBong;
    }

}
