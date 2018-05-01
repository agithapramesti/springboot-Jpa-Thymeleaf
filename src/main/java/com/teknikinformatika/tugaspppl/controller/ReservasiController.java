package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.SearchModel;
import com.teknikinformatika.tugaspppl.service.CabangService;
import com.teknikinformatika.tugaspppl.service.JenisKamarService;
import com.teknikinformatika.tugaspppl.service.KamarService;
import com.teknikinformatika.tugaspppl.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservasiController {
    @Autowired
    private  ReservasiService reservasiService;
    @Autowired
    private JenisKamarService jenisKamarService;
    @Autowired
    private CabangService cabangService;
    @Autowired
    private KamarService kamarService;
    @RequestMapping(value = {"/reservasi"},method = RequestMethod.GET)
    public String manageReservasi(Model model, Authentication authentication) {

        model= reservasiService.manageFormCobaReservasi(model,authentication);
        return "reservasi";
    }
    @RequestMapping(value = {"/reservasi/search"},method = RequestMethod.POST)
    public String manageSearchReservasi(Model model, SearchModel searchModel) {
        return reservasiService.manageSearchKamarAvailable(model,searchModel);
    }
    @RequestMapping(value = {"/tambahKamarReservasi"},method = RequestMethod.GET)
    public String manageTambahKamarReservasi(Model model, Authentication authentication) {
        model = reservasiService.getAllKamarTersediaReservasi(model);
        return "tambahKamarReservasi";
    }
    @RequestMapping(value = {"/tambahKamarReservasi/tambah/{id}"})
    public String moveToCart(@PathVariable("id") int id, Model model) {

        model = reservasiService.manageMoveToCart(id, model);
        return "redirect:/tambahKamarReservasi";
    }
}
