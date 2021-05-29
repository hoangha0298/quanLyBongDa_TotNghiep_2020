package com.javatools.crud.service;

import com.javatools.crud.business.excel.trandau.TranDauSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TranDauService {

    private int sheet = 0;

    // Hoàng code
    public void saveLessionList(MultipartFile myFile) {
        TranDauSheet tranDauSheet = new TranDauSheet(myFile, sheet);
        tranDauSheet.loadAllRows();
    }

    public String deleteAllLession() {
//        lessionRepo.deleteAll();
        return "delete all Lession";
    }
    
}
