package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.season.SeasonDao;
import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservasiService {
    @Autowired
    private CabangDao cabangDao;
    @Autowired
    private SeasonDao seasonDao;
    public Model manageFormCustomerPage(Model model){
        model.addAttribute("season",seasonDao.getAllSeasonYogyakarta());
        model.addAttribute("seasonB",seasonDao.getAllSeasonBandung());
        model.addAttribute("user", new User());
        model.addAttribute("cabang",cabangDao.getAllCabang());
        return model;
    }

}
