package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.ReportDao;
import com.teknikinformatika.tugaspppl.dao.reservasi.ReservasiDao;
import com.teknikinformatika.tugaspppl.model.PendapatanBulanan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ReservasiDao reservasiDao;
    public Model getAllListPendapatanBulanan(Model model){
        model.addAttribute("incomeBln", reportDao.getAllPendapatanBulanan());
        model.addAttribute("incomeBlnYgy", reportDao.getAllPendapatanBulananCabangYogya());
        model.addAttribute("incomeBlnBdg", reportDao.getAllPendapatanBulananCabangBandung());
        model.addAttribute("totalSeluruh", reservasiDao.getTotalPerulan());

        model.addAttribute("tahun",reservasiDao.getTahunIni());
        return model;
    }
    public Model getAllCustomerBaru(Model model){
        model.addAttribute("customer", reportDao.getAllCustomerBaruYearly());
        model.addAttribute("total",reservasiDao.getTotalCustomerBaru());
        model.addAttribute("tahun",reservasiDao.getTahunIni());
        return model;
    }
    public Model getJumlahTamu(Model model){
        model.addAttribute("jmlTamu",reportDao.getJumlahTamuJenisKamar());
        model.addAttribute("total",reservasiDao.totalJumlahTamuJenisKamar());

        return model;
    }
    public Model getCustomerTerbanyak(Model model){
        model.addAttribute("customer",reportDao.getCustomerTerbanyak());
        return model;
    }
    public Model getPedapatanCabangPertahun(Model model){
        model.addAttribute("pendapatan",reportDao.getAllPendapatanCabangPertahun());
        model.addAttribute("total",reservasiDao.totalPendapatanCabangTahunan());
        return model;
    }
}
