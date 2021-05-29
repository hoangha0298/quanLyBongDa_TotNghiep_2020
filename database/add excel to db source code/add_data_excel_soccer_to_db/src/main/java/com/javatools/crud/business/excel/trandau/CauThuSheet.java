package com.javatools.crud.business.excel.trandau;

import com.javatools.crud.business.excel.SheetToObject;
import com.javatools.crud.model.CauThu;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;

public class CauThuSheet extends SheetToObject {
    // mapping cột excel vào thuộc tính đối tượng, giá trị biến là vị trí cột trái sang phải bắt đầu từ 0, cột = -1 là không dùng tới
    public static int id = -1;
    public static int hoVaTen = columnToInt('b');
    public static int ngaySinh = columnToInt('d');
    public static int tuoi = columnToInt('c');
    public static int viTri = columnToInt('g');
    public static int doiBong = columnToInt('h');
    public static int quocTich = columnToInt('l');
    public static int tongSoBan = columnToInt('p');
    public static int soBanSanNha = columnToInt('q');
    public static int soBanSanKhach = columnToInt('r');
    public static int tongHoTro = columnToInt('s');
    public static int hoTroSanNha = columnToInt('t');
    public static int hoTroSanKhach = columnToInt('u');

    public static int countNextRow = 1;

    public CauThuSheet(MultipartFile fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    public CauThuSheet(InputStream fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    private int idCauThu = 1;
    @Override
    protected CauThu doWithRow(Row row) {
        CauThu cauThu = new CauThu();

        cauThu.setId(idCauThu++);
        cauThu.setHoVaTen(getStringCellValue(row, hoVaTen));
        cauThu.setQuocTich(getStringCellValue(row, quocTich));
        cauThu.setNgaySinh(new Timestamp(Long.valueOf(getStringCellValue(row, ngaySinh)) * 1000));
        cauThu.setTuoi(Integer.valueOf(getStringCellValue(row, tuoi)));
        cauThu.setViTri(getStringCellValue(row, viTri));
        cauThu.setDoiBong(getStringCellValue(row, doiBong));
        cauThu.setTongSoBan(Integer.valueOf(getStringCellValue(row, tongSoBan)));
        cauThu.setSoBanSanNha(Integer.valueOf(getStringCellValue(row, soBanSanNha)));
        cauThu.setSoBanSanKhach(Integer.valueOf(getStringCellValue(row, soBanSanKhach)));
        cauThu.setTongHoTro(Integer.valueOf(getStringCellValue(row, tongHoTro)));
        cauThu.setHoTroSanNha(Integer.valueOf(getStringCellValue(row, hoTroSanNha)));
        cauThu.setHoTroSanKhach(Integer.valueOf(getStringCellValue(row, hoTroSanKhach)));

        datas.add(cauThu);
        return cauThu;
    }

}
