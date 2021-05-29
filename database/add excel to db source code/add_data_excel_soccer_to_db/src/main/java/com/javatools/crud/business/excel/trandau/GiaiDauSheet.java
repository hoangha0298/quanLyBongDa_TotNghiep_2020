package com.javatools.crud.business.excel.trandau;

import com.javatools.crud.business.excel.SheetToObject;
import com.javatools.crud.model.GiaiDau;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class GiaiDauSheet extends SheetToObject<GiaiDau> {

    // mapping cột excel vào thuộc tính đối tượng, giá trị biến là vị trí cột trái sang phải bắt đầu từ 0, cột = -1 là không dùng tới
    public static int id = -1;
    public static int ten = columnToInt('a');
    public static int muaGiai = columnToInt('b');
    public static int soDoiThamDu = columnToInt('e');
    public static int soVongDau = columnToInt('i');

    public static int countNextRow = 1;

    public GiaiDauSheet(MultipartFile fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    public GiaiDauSheet(InputStream fileExcel, Integer indexSheet){
        super(fileExcel, indexSheet, countNextRow);
    }

    private int idGiaiDau = 1;
    @Override
    protected GiaiDau doWithRow(Row row) {
        GiaiDau giaiDau = new GiaiDau();

        giaiDau.setId(idGiaiDau++);
        giaiDau.setTen(getStringCellValue(row, ten));
        giaiDau.setMuaGiai(getStringCellValue(row, muaGiai));
        giaiDau.setSoDoiThamDu(Integer.valueOf(getStringCellValue(row, soDoiThamDu)));
        giaiDau.setSoVongDau(Integer.valueOf(getStringCellValue(row, soVongDau)));

        datas.add(giaiDau);
        return giaiDau;
    }

}
