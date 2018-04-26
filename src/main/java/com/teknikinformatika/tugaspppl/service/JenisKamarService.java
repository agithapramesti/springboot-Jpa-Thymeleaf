package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarDao;
import com.teknikinformatika.tugaspppl.model.JenisKamar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisKamarService {
    @Autowired
    JenisKamarDao jenisKamarDao;

    public Model getAllJenisKamars(Model model){
        model.addAttribute("jenisKamar", jenisKamarDao.findAll());
        return model;
    }
    public Model manageEditJenisKamar(Model model, int id){
        model.addAttribute("jenisKamar",jenisKamarDao.getOne(id));
        return model;
    }
    public String simpanJenisKamar(Model model, JenisKamar jenisKamar){
        jenisKamarDao.save(jenisKamar);
        return "redirect:/dataJenisKamar";
    }
    public String softDeleteJenisKamar(int id){

        jenisKamarDao.softDelete(id);
        return "redirect:/dataJenisKamar";
    }
}
