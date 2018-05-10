package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.SearchModel;
import com.teknikinformatika.tugaspppl.model.SearchTambahDurasi;
import com.teknikinformatika.tugaspppl.service.CabangService;
import com.teknikinformatika.tugaspppl.service.JenisKamarService;
import com.teknikinformatika.tugaspppl.service.KamarService;
import com.teknikinformatika.tugaspppl.service.ReservasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String manageTambahKamarReservasi(Model model) {
        model = reservasiService.getAllKamarTersediaReservasi(model);
        return "tambahKamarReservasi";
    }
    @RequestMapping(value = {"/tambahKamarReservasi/tambah/{id}"})
    public String pindahCart(@PathVariable("id") int id, Model model,@ModelAttribute("quantity") int quantity) {
        System.out.println("hai: "+id);
        System.out.println("quanti"+quantity);
        model = reservasiService.manageMoveToCart(id, model, quantity);
        return "redirect:/tambahKamarReservasi";
    }
    @RequestMapping(value = {"/tambahKamarReservasi/hapus/{id}"})
    public String hapusDariCart(@PathVariable("id") int id){
        reservasiService.hapusCartById(id);
        return "redirect:/tambahKamarReservasi";
    }
    @RequestMapping(value = {"/tambahKamarReservasi/kembali"})
    public String kembaliKeHalamanAwal(){
        reservasiService.hapusAllCart();
        return "redirect:/reservasi";
    }
    @RequestMapping(value = {"/tambahFasilitasBerbayar"})
    public String tambahFasilitasBerbayar(Model model,Authentication authentication){
        reservasiService.manageReservasiAndDetails(authentication,model);
        return "tambahFasilitasBerbayar";
    }
    @RequestMapping(value = {"/nota"})
    public String hasilReservasi(Model model){

        return "nota";
    }
    @RequestMapping(value = {"/nota"},method = RequestMethod.POST)
    public String tambahFasilitasBerbayarPost(Model model,@ModelAttribute("extraBed") int extraBed,
                                              @ModelAttribute("laundryRegularPotong") int laundryRegularPotong,
                                              @ModelAttribute("laundryFastServicePotong") int laundryFastServicePotong,
                                              @ModelAttribute("massageOrang") int massageOrang,
                                              @ModelAttribute("minibarMinuman") int minibarMinuman,
                                              @ModelAttribute("tambahanBreakfastOrang") int tambahanBreakfastOrang,
                                              @ModelAttribute("lunchPackageOrang") int lunchPackageOrang,
                                              @ModelAttribute("dinnerPackageOrang") int dinnerPackageOrang,
                                              @ModelAttribute("meetingRoomFullDayOrang") int meetingRoomFullDayOrang){
        reservasiService.manageReservasiFasilitasBerbayar(model,extraBed,laundryRegularPotong,laundryFastServicePotong,
                massageOrang,minibarMinuman,tambahanBreakfastOrang,lunchPackageOrang,dinnerPackageOrang,meetingRoomFullDayOrang);
        reservasiService.manageDetailReservasi(model);
        reservasiService.kalkulasiFasilitasBerbayar();
        reservasiService.kalkulasiTotalPembayaran();
        return "redirect:/nota";
    }

    @RequestMapping(value = {"/kelolaReservasi"})
    public String kelolaReservasi(Model model){
        reservasiService.manageFormKelolaReservasi(model);
        return "kelolaReservasi";
    }
    @RequestMapping(value = {"/kelolaReservasi/search"},method = RequestMethod.POST)
    public String manageKelolaDurasi(Model model, SearchTambahDurasi searchModel) {
        return reservasiService.manageTambahDurasi(searchModel,model);
    }

}
