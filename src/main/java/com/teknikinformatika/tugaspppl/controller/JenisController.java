package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.JenisKamar;
import com.teknikinformatika.tugaspppl.service.JenisKamarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JenisController {
    @Autowired
    private JenisKamarService jenisKamarService;
    @RequestMapping(value = {"/dataJenisKamar"},method = RequestMethod.GET)
    public String getAllJenisKamar(Model model){
        model = jenisKamarService.getAllJenisKamars(model);
        return "/admin/dataJenisKamar";
    }
    @RequestMapping(value = {"/dataJenisKamar"},method = RequestMethod.POST)
    public String simpanJenisKamar(@ModelAttribute("jenisKamar")JenisKamar jenisKamar, Model model){
        return jenisKamarService.simpanJenisKamar(model, jenisKamar);
    }
    @RequestMapping(value = "/dataJenisKamar/editJenisKamar/{id}", method = RequestMethod.GET)
    public String editJenisKamar(@PathVariable int id, Model model){
        model = jenisKamarService.manageEditJenisKamar(model, id);
        return "/admin/editJenisKamar";
    }
    @RequestMapping(value = "/dataJenisKamar/softDelete/{id}", method = RequestMethod.GET)
    public String softdeleteJenisK(@PathVariable int id){
        return jenisKamarService.softDeleteJenisKamar(id);
    }
}
