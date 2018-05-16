package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LaporanController {
    @Autowired
    private ReportService reportService;
    @RequestMapping(value = {"/laporan"},method = RequestMethod.GET)
    public String dataKamar() {

        return "/admin/laporan";
    }
    @RequestMapping(value = "/laporanPendapatanBulanan",method = RequestMethod.GET)
    public String laporanPendapatanBulanan(Model model){
        reportService.getAllListPendapatanBulanan(model);
        return "/admin/laporanPendapatanBulanan";
    }
    @RequestMapping(value = "/laporanCustomerBaru",method = RequestMethod.GET)
    public String laporanCustomerBaru(Model model){
        reportService.getAllCustomerBaru(model);
        return "/admin/laporanCustomerBaru";
    }
    @RequestMapping(value = "/laporanJumlahTamu",method = RequestMethod.GET)
    public String laporanJumlahTamu(Model model){
        reportService.getJumlahTamu(model);
        return "/admin/laporanJumlahTamu";
    }
    @RequestMapping(value = "/laporanCustomerReservasiTerbanyak",method = RequestMethod.GET)
    public String laporanCustomerReservasiTerbanyak(Model model){
        reportService.getCustomerTerbanyak(model);
        return "/admin/laporanCustomerReservasiTerbanyak";
    }
    @RequestMapping(value = "/laporanPendapatanCabangPertahun",method = RequestMethod.GET)
    public String laporanPendapatanCabangPertahun(Model model){
        reportService.getPedapatanCabangPertahun(model);
        return "/admin/laporanPendapatanCabangPertahun";
    }
}
