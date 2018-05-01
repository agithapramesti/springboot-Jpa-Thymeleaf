package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarDao;
import com.teknikinformatika.tugaspppl.dao.kamar.KamarDao;
import com.teknikinformatika.tugaspppl.dao.reservasi.ReservasiTempDao;
import com.teknikinformatika.tugaspppl.dao.season.SeasonDao;
import com.teknikinformatika.tugaspppl.dao.user.UserDao;
import com.teknikinformatika.tugaspppl.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ReservasiService {
    @Autowired
    private CabangDao cabangDao;
    @Autowired
    private SeasonDao seasonDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private KamarDao kamarDao;
    @Autowired
    private JenisKamarDao jenisKamarDao;
    @Autowired
    private ReservasiTempDao tempDao;
    private ReservasiTemp reservasiTemp ;
    public Model manageFormCustomerPage(Model model){
        model.addAttribute("season",seasonDao.getAllSeasonYogyakarta());
        model.addAttribute("seasonB",seasonDao.getAllSeasonBandung());
        model.addAttribute("user", new User());
        model.addAttribute("cabang",cabangDao.getAllCabang());
        return model;
    }

    public Model manageFormCobaReservasi(Model model,Authentication authentication){
        int userId = 0;
        userId = userDao.getIdByUsername(authentication.getName());

        model.addAttribute("cabang",cabangDao.getAllCabang());
        model.addAttribute("search",new SearchModel());
        return model;
    }
    public String manageSearchKamarAvailable(Model model,SearchModel searchModel){
        GlobalVariable.cabangId = searchModel.getCabangId();
        GlobalVariable.jumlahAnak = searchModel.getJumlahAnak();
        GlobalVariable.jumlahDewasa = searchModel.getJumlahDewasa();
        GlobalVariable.tanggalCheckIn = searchModel.getTanggalCheckIn();
        GlobalVariable.tanggalCheckOut = searchModel.getTanggalCheckOut();
        return "redirect:/tambahKamarReservasi";
    }
    public Model getAllKamarTersediaReservasi(Model model){
        model.addAttribute("kamar",kamarDao.getAllKamarTersediaReservasi(GlobalVariable.tanggalCheckIn,GlobalVariable.tanggalCheckOut,GlobalVariable.cabangId));
        model.addAttribute("kamarTemp",tempDao.findAll());
        return model;
    }
    public Model manageMoveToCart(int id, Model model){
        reservasiTemp = new ReservasiTemp();

        Kamar kamar = kamarDao.getOne(id);


        reservasiTemp.setJumlahAnak(GlobalVariable.jumlahAnak);

        reservasiTemp.setJumlahKamar(1);

        reservasiTemp.setJumlahDewasa(GlobalVariable.jumlahDewasa);

        reservasiTemp.setTanggalCheckIn(GlobalVariable.tanggalCheckIn);

        reservasiTemp.setTanggalCheckOut(GlobalVariable.tanggalCheckOut);

        reservasiTemp.setHarga(kamar.getJenisKamar().getHargaJenisKamar());

        reservasiTemp.setKamarId(id);

        reservasiTemp.setCabangId(GlobalVariable.cabangId);

        reservasiTemp.setKodeKamar(kamar.getKodeKamar());

        reservasiTemp.setNamaJenisKamar(kamar.getJenisKamar().getNamaJenisKamar());

        // ini nanti di anuin sm durasi dan harga season

        tempDao.save(reservasiTemp);

        return model;
    }

}
