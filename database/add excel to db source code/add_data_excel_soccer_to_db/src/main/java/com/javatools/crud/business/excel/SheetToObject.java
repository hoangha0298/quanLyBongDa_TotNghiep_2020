package com.javatools.crud.business.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

// FormulaEvaluator hỗ trợ không tốt , nên copy sang cột khác set cứng giá trị
public abstract class SheetToObject<T> {

    // quản lý các dòng của file excel
    protected Iterator<Row> rowIterator;
    // dùng để chạy các cell là hàm
    protected FormulaEvaluator formulaEvaluator;
    protected int countRows;
    // có thể lưu dữ liệu các dòng vào đây , tùy doWithRow cài đặt
    protected ArrayList<T> datas = new ArrayList<>();

    // a-0, b-1, c-2, ....
    public static int columnToInt (char column) {
        return Character.toLowerCase(column) - 97;
    }

    // indexSheet vị trí sheet trong file excel
    // countNextRow số dòng bỏ qua lúc đầu (title)
    public SheetToObject(MultipartFile fileExcel, int indexSheet, int countNextRow) {
        try {
            this.contructor(fileExcel.getInputStream(), indexSheet,countNextRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SheetToObject(InputStream fileExcel, int indexSheet, int countNextRow){
        contructor(fileExcel, indexSheet, countNextRow);
    }

    public void contructor(InputStream fileExcel, int indexSheet, int countNextRow) {
        try {
            Workbook workbook = new XSSFWorkbook(fileExcel);
            System.out.println
                    ("============ sheet name = " + workbook.getSheetAt(indexSheet).getSheetName() + " ============");

            rowIterator = workbook.getSheetAt(indexSheet).rowIterator();
            // bỏ qua các hàng title
            while (countNextRow-- > 0) {
                rowIterator.next();
            }
            formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> loadAllRows() {
        System.out.println(forEachRow());
        return  datas;
    }

    // duyệt toàn bộ hàng của sheet và chạy doWithRow với mỗi hàng
    // trả về số lượng hàng đã duyệt
    public int forEachRow() {
        countRows = 0;
        // duyệt tất cả hàng
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println("================== row " + row.getRowNum() + " ==================");

            // khi giá trị cột 1 không có thì thoát (mặc dù cột khác có giá trị)
            CellValue cellValue = processCellValue(row, 1);
            if (cellValue == null || cellValue.toString().equals("[]")) {
                break;
            }

            // đọc 1 dòng, nếu trả về null thì thoát luôn
            countRows++;
            T object = doWithRow(row);
            if (object == null) break;
            System.out.println(object);
        }

        return countRows;
    }

    // làm việc với 1 row trả về đối tượng T, nếu T = null thì thoát vòng lặp
    protected abstract T doWithRow(Row row);

    public ArrayList<T> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
    }

    // xử lý evaluator (công thức trong excel) nếu có lỗi thì trả về null
    protected CellValue processCellValue(Row row, int column) {
        try {
            return formulaEvaluator.evaluate(row.getCell(column));
        } catch (Exception e) {
            System.out.println("\ncontent cell error = " + row.getCell(column));
            e.printStackTrace();
            return null;
        }
    }

    // trả giá trị int nếu lỗi trả về 0
    protected int evaluatorGetNumber(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return (int) cellValue.getNumberValue();
        }
        return 0;
    }

    // trả giá trị string nếu lỗi trả về rỗng
    protected String evaluatorGetString(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getStringValue();
        }
        return "";
    }

    // trả giá trị boolean nếu lỗi trả về false
    protected boolean evaluatorGetBoolean(Row row, int column) {
        CellValue cellValue = processCellValue(row, column);
        if (cellValue != null) {
            return cellValue.getBooleanValue();
        }
        return false;
    }

    // trả giá trị string từ cell nếu lỗi trả về rỗng
    protected String getStringCellValue(Row row, int column) {
        Cell temp = row.getCell(column);
        if (temp != null) {
            return temp.getStringCellValue();
        }
        return "";
    }

    // trả giá trị date từ cell nếu lỗi trả về rỗng
    protected Date getDateCellValue(Row row, int column) {
        Cell temp = row.getCell(column);
        if (temp != null) {
            if (temp.getCellType() == CellType.NUMERIC) {
                return temp.getDateCellValue();
            }
            else if (temp.getCellType() == CellType.STRING) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    return df.parse(temp.getStringCellValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
