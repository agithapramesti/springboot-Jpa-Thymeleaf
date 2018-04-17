package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarDao;
import com.teknikinformatika.tugaspppl.dao.kamar.KamarDao;
import com.teknikinformatika.tugaspppl.dao.tipe_kasur.TipeKasurDao;
import com.teknikinformatika.tugaspppl.model.Cabang;
import com.teknikinformatika.tugaspppl.model.Kamar;
import com.teknikinformatika.tugaspppl.model.TipeKasur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

@Service
@Transactional
public class KamarService {
    @Autowired
    private KamarDao kamarDao;
    @Autowired
    private TipeKasurDao tipeKasurDao;
    @Autowired
    private JenisKamarDao jenisKamarDao;
    @Autowired
    private CabangDao cabangDao;
    public Model getAllKamars(Model model){
        model.addAttribute("kamar",kamarDao.findAll());
        return model;
    }

    public Model manageTambahKamar(Model model){
        model.addAttribute("kamar",new Kamar());
        model.addAttribute("tipe",tipeKasurDao.findAll());
        model.addAttribute("cabang",cabangDao.getAllCabang());
        model.addAttribute("jenisKamars", jenisKamarDao.findAll());
        return model;
    }
    public Model manageEditKamar(Model model, int id){
        model.addAttribute("kamar",kamarDao.findById(id));
        model.addAttribute("tipe",tipeKasurDao.findAll());
        model.addAttribute("jenisKamars", jenisKamarDao.findAll());
        return model;
    }
    private String generateKodeKamar(int temp, int x, int y){

        String xy ="";
        if(temp == 3){

            xy= "0"+x+"0"+y+"ED";
        }
        else if(temp == 2){
            xy= "0"+x+"0"+y+"DD";
        }
        else if(temp == 4){

            xy= "0"+x+"0"+y+"JS";
        }
        else
        {

            xy= "0"+x+"0"+y+"S";
        }

        return xy;
    }

    public String saveKamar(Kamar kamar,Model model){
        String temp= "";
        kamar.setStatusKamar(1);
        temp = generateKodeKamar(kamar.getJenisKamar().getJenisKamarId(),kamar.getLantai(),kamar.getNomorKamar());
        kamar.setKodeKamar(temp);

        kamarDao.save(kamar);
        return "redirect:/dataKamar";
    }

    public Model tambahCabangKamar(Model model, int id){
        model.addAttribute("kamar",kamarDao.getOne(id));
        model.addAttribute("cabang", cabangDao.getAllCabangNotIncludedByKamarId(id));
        return model;
    }
    public String simpanCabangKamar(int kamarId, int cabangId, Model model){

        Cabang cabang = cabangDao.getOne(cabangId);
        Kamar kamar = kamarDao.getOne(kamarId);
        if(kamar != null){

            if(!kamar.hasCabang(cabang)){

                kamar.getCabangs().add(cabang);
            }
            kamarDao.save(kamar);

            model.addAttribute("kamar",kamarDao.getOne(kamarId));
            model.addAttribute("cabang", cabangDao.findAll());
            return "redirect:/dataKamar";
        }

        return "redirect:/dataKamar";

    }
}
