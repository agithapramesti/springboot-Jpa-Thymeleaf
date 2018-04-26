package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.Cabang;
import com.teknikinformatika.tugaspppl.model.Kamar;
import com.teknikinformatika.tugaspppl.service.CabangService;
import com.teknikinformatika.tugaspppl.service.KamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KamarController {
    @Autowired
    private KamarService kamarService;
    @Autowired
    private CabangService cabangService;
    @RequestMapping(value = "/exceptionHandlingKamar")
    public String exceptionHandling() {

        return "/admin/exceptionHandlingKamar";
    }
    @RequestMapping(value = {"/dataKamar"},method = RequestMethod.GET)
    public String dataKamar(Model model) {
        model= kamarService.getAllKamars(model);
        return "/admin/dataKamar";
    }
    @RequestMapping(value = {"/dataKamar/tambahKamar"},method = RequestMethod.GET)
    public String manageTambahKamar(Model model) {

        model= kamarService.manageTambahKamar(model);
        return "/admin/tambahKamar";
    }
    @RequestMapping(value = {"/dataKamar/kamarTersedia"},method = RequestMethod.GET)
    public String kamarTersedia(Model model){
        model = kamarService.getAllKamarsTersedia(model);
        return "/admin/dataKamarTersedia";
    }
    @RequestMapping(value = {"/dataKamar/kamarTidakTersedia"},method = RequestMethod.GET)
    public String kamarTidakTersedia(Model model){
        model = kamarService.getAllKamarsTidakTersedia(model);
        return "/admin/dataKamarTidakTersedia";
    }
    @RequestMapping(value = {"/dataKamar"},method = RequestMethod.POST)
    public String simpanKamar(@ModelAttribute("kamar")Kamar kamar, Model model) {
        int id = kamar.getKamarId();
        List<Cabang> cabangs = cabangService.getAllCabangByKamarId(id);
        kamar.setCabangs(cabangs);
        return kamarService.saveKamar(kamar,model);
    }
    @RequestMapping(value = "/dataKamar/editKamar/{id}", method = RequestMethod.GET)
    public String editKamar(@PathVariable int id, Model model) {
        model = kamarService.manageEditKamar(model, id);
        return "/admin/editKamar";
    }
    @RequestMapping(value = "/dataKamar/softdelete/{id}",method = RequestMethod.GET)
    public String softDeleteKamar(@PathVariable int id){
        kamarService.softDeleteKamar(id);
        return "redirect:/dataKamar";
    }

    @RequestMapping(value="/datakamar/{id}/cabang", method=RequestMethod.GET)
    public String simpanKamarCabang(@PathVariable int id, @RequestParam Integer cabangId, Model model) {
        System.out.println("mboh");
        System.out.println("cek1 adalah:"+cabangId+"wow");

        return kamarService.simpanCabangKamar(id, cabangId, model);
    }
    @RequestMapping(value = "tambahCabangKamar/{id}", method = RequestMethod.GET)
    public String tambahCabang(@PathVariable("id") int id, Model model){
        System.out.println("cek2");
        model = kamarService.tambahCabangKamar(model, id);
        return "/admin/tambahCabangKamar";
    }
}
