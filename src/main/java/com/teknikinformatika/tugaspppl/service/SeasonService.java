package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.season.SeasonDao;

import com.teknikinformatika.tugaspppl.model.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SeasonService {
    @Autowired
    SeasonDao seasonDao;
    @Autowired
    CabangDao cabangDao;

    public Model getAllSeasonFromDB(Model model){
        model.addAttribute("season",seasonDao.findAll());
        return model;
    }
    public List<String> getAllAvailableSeason(){
        ArrayList<String> nama = new ArrayList<>();
        nama.add("High Season");
        nama.add("Promo");
        nama.add("Normal");

        return nama;
    }
    public Model manageTambahSeason(Model model){
        model.addAttribute("season",new Season());
        model.addAttribute("namaSeason", getAllAvailableSeason());
        model.addAttribute("cabang", cabangDao.getAllCabang());
        return model;
    }
    public String save(Model model, Season season){

        if(season.getStatuSeason()==0){
            season.setStatuSeason(1);
        }
        seasonDao.save(season);
        return "redirect:/season";
    }
    public Model manageEditSeason(Model model, int id){
        model.addAttribute("season",seasonDao.findBySeasonId(id));
        model.addAttribute("cabang", cabangDao.getAllCabang());
        return model;
    }
    public String softDelete(int id){

        seasonDao.softDelete(id);
        return "redirect:/season";
    }
    public Model searchSeasonByNamaSeason(Model model, String namaSeason){

        model.addAttribute("season",seasonDao.getAllSeasonByNamaSeason(namaSeason));
        return model;
    }

}
