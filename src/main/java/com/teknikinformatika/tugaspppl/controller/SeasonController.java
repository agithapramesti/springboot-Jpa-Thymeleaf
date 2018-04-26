package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.Season;
import com.teknikinformatika.tugaspppl.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SeasonController {
    @Autowired
    private SeasonService seasonService;
    @RequestMapping(value = {"/season"}, method = RequestMethod.GET)
    public String showAlllSeason(Model model) {
        model= seasonService.getAllSeasonFromDB(model);
        return "/admin/season";
    }
    @RequestMapping(value = {"/season"},method = RequestMethod.POST)
    public String simpanSeason(@ModelAttribute("season")Season season, Model model) {
        return seasonService.save(model,season);
    }
    @RequestMapping(value = {"/season/tambahSeason"},method = RequestMethod.GET)
    public String manageTambahSeason(Model model) {
        model= seasonService.manageTambahSeason(model);
        return "/admin/tambahSeason";
    }
    @RequestMapping(value = "/season/editSeason/{id}", method = RequestMethod.GET)
    public String editDataSeason(@PathVariable int id, Model model) {
        model = seasonService.manageEditSeason(model, id);
        return "/admin/tambahSeason";
    }
    @RequestMapping(value = "season/softdelete/{id}",method = RequestMethod.GET)
    public String softDeleteSeason(@PathVariable int id){
        return seasonService.softDelete(id);

    }
    @RequestMapping(value = "season/cari",method = RequestMethod.GET)
    public String cariBerdasarkanNamaSeason(Model model, @ModelAttribute("namaSeason") String namaSeason){

        model=seasonService.searchSeasonByNamaSeason(model,namaSeason);
        return "/admin/season";
    }

}
