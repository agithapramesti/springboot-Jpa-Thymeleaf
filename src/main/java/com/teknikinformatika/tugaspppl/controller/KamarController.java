package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.Kamar;
import com.teknikinformatika.tugaspppl.service.KamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KamarController {
    @Autowired
    private KamarService kamarService;
    @RequestMapping(value = {"/dataKamar"},method = RequestMethod.GET)
    public String dataKamar(Model model) {
        model= kamarService.getAllKamars(model);
        return "/admin/dataKamar";
    }
    @RequestMapping(value = {"/dataKamar/tambahKamar"},method = RequestMethod.GET)
    public String manageTambahKamar(Model model) {
        System.out.println("masuk controller2");
        model= kamarService.manageTambahKamar(model);
        return "/admin/tambahKamar";
    }
    @RequestMapping(value = {"/dataKamar"},method = RequestMethod.POST)
    public String simpanKamar(@ModelAttribute("kamar")Kamar kamar, Model model) {
        System.out.println("masuk controller1");

        return kamarService.saveKamar(kamar,model);
    }
    @RequestMapping(value = "/dataKamar/editKamar/{id}", method = RequestMethod.GET)
    public String editKamar(@PathVariable int id, Model model) {
        model = kamarService.manageEditKamar(model, id);
        return "/admin/tambahKamar";
    }

    @RequestMapping(value="/datakamar/{id}/cabang", method=RequestMethod.GET)
    public String simpanKamarCabang(@PathVariable int id, @RequestParam int cabangId, Model model) {
        System.out.println("hey gendut");
        return kamarService.simpanCabangKamar(id, cabangId, model);
    }
    @RequestMapping(value = "tambahCabangKamar/{id}", method = RequestMethod.GET)
    public String tambahCabang(@PathVariable("id") int id, Model model){

        model = kamarService.tambahCabangKamar(model, id);
        return "/admin/tambahCabangKamar";
    }
}
