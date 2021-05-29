package com.example.demo.controller;

import com.example.demo.model.dto.ResponseDTO;
import com.example.demo.model.dto.SuKienTranDauDTO;
import com.example.demo.service.SuKienService;
import com.example.demo.service.SuKienTranDauService;
import com.example.demo.service.TranDauService;
import com.example.demo.service.VongDauService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Value("${vong_dau.message}")
    private String message;

    @Autowired
    TranDauService tranDauService;
    @Autowired
    VongDauService vongDauService;
    @Autowired
    SuKienService suKienService;
    @Autowired
    SuKienTranDauService suKienTranDauService;

    @RequestMapping(value = {"/", "/vong_dau"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        request.setAttribute("vongDauService", vongDauService);
        return "vong_dau";
    }

    @RequestMapping(value = {"/tran_dau"}, method = RequestMethod.GET)
    public String tranDau(Model model, HttpServletRequest request, @RequestParam int id) {
        request.setAttribute("tranDauService", tranDauService);
        request.setAttribute("suKienService", suKienService);
        return "tran_dau";
    }

    @ResponseBody
    @RequestMapping(value = {"/tran_dau/them_su_kien"}, method = RequestMethod.POST)
    public ResponseDTO themSuKien(Model model, HttpServletRequest request, @RequestBody SuKienTranDauDTO suKienTranDauDTO) {
        Session session = (Session) request.getAttribute("session");
        return suKienTranDauService.addSuKienTranDau(session, suKienTranDauDTO);
    }

    @ResponseBody
    @RequestMapping(value = {"/tran_dau/xoa_su_kien"}, method = RequestMethod.POST)
    public ResponseDTO xoaSuKien(Model model, HttpServletRequest request, @RequestBody SuKienTranDauDTO suKienTranDauDTO) {
        Session session = (Session) request.getAttribute("session");
        return suKienTranDauService.removeSuKienTranDau(session, suKienTranDauDTO);
    }

}
