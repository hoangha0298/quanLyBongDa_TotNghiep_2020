package com.javatools.crud.controller;


import com.javatools.crud.service.TranDauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ToolController {
    @Autowired
    private TranDauService tranBongSheet;

    @GetMapping("/index")
    public String indexDm() {
        return "index";
    }

    @RequestMapping(value = "/toolDeleteAll",method= {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteAll(Model model){
        tranBongSheet.deleteAllLession();
        model.addAttribute("message", "delete success");
        return "testDemo";
    }

    @RequestMapping(value = "/toolAddFromExcel",method = {RequestMethod.POST})
    public String addAll(@RequestParam MultipartFile myFile, Model model) {
        model.addAttribute("message", "add success");
        tranBongSheet.saveLessionList(myFile);
        return "testDemo";
    }
}
