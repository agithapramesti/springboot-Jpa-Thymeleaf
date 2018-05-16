package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.dao.user.UserDao;
import com.teknikinformatika.tugaspppl.model.Reservasi;
import com.teknikinformatika.tugaspppl.model.SearchModel;
import com.teknikinformatika.tugaspppl.model.User;
import com.teknikinformatika.tugaspppl.service.ReservasiService;
import com.teknikinformatika.tugaspppl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PelangganController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservasiService reservasiService;
    @RequestMapping(value = {"/halamanPelanggan"},method = RequestMethod.GET)
    public String halamanPelanggan(Model model, Authentication authentication) {

        model = userService.kelolaDataPelanggan(model,authentication);
        return "/customer/halamanPelanggan";
    }
    @RequestMapping(value = {"/ubahProfil"},method = RequestMethod.GET)
    public String manageUbahProfil(Model model, Authentication authentication){
        model = userService.manageUbahProfilAkun(model,authentication);
        return "/customer/ubahProfil";
    }
    @RequestMapping(value = {"/ubahProfil"},method = RequestMethod.POST)
    public String simpanPelanggan(@ModelAttribute("users")User user, Model model) {

        return userService.ubahProfil(model,user);
    }
    @RequestMapping(value = {"/reservasiPelanggan"},method = RequestMethod.GET)
    public String reservasiPelanggan(Model model, Authentication authentication){
        model= reservasiService.manageFormCobaReservasi(model,authentication);
        return "/customer/reservasiPelanggan";
    }
    @RequestMapping(value = {"/reservasiPelanggan/search"},method = RequestMethod.POST)
    public String manageSearchReservasi(Model model, SearchModel searchModel) {
        return reservasiService.manageSearchKamarReservasiPelangganAvailable(model,searchModel);
    }
    @RequestMapping(value = {"/tambahKamarReservasiPelanggan"},method = RequestMethod.GET)
    public String tambahKamarReservasiPelanggan(Model model) {
        model = reservasiService.getAllKamarTersediaReservasi(model);
        return "/customer/tambahKamarReservasiPelanggan";
    }
    @RequestMapping(value = {"/tambahKamarReservasiPelanggan/tambah/{id}"})
    public String pindahCart(@PathVariable("id") int id, Model model, @ModelAttribute("quantity") int quantity) {

        model = reservasiService.manageMoveToCart(id, model, quantity);
        return "redirect:/tambahKamarReservasiPelanggan";
    }
    @RequestMapping(value = {"/tambahKamarReservasiPelanggan/hapus/{id}"})
    public String hapusDariCart(@PathVariable("id") int id){
        reservasiService.hapusCartById(id);
        return "redirect:/tambahKamarReservasiPelanggan";
    }
    @RequestMapping(value = {"/tambahKamarReservasiPelanggan/kembali"})
    public String kembaliKeHalamanAwal(){
        reservasiService.hapusAllCart();
        return "redirect:/reservasiPelanggan";
    }
    @RequestMapping(value = {"/tambahFasilitasBerbayarPelanggan"})
    public String tambahFasilitasBerbayar(Model model,Authentication authentication){
        reservasiService.manageReservasiAndDetails(authentication,model);
        return "/customer/tambahFasilitasBerbayarPelanggan";
    }
    @RequestMapping(value = {"/notaPelanggan"})
    public String hasilReservasi(Model model,Authentication authentication){
        reservasiService.manageReservasiNota(model,authentication);
        return "/customer/notaPelanggan";
    }
    @RequestMapping(value = {"/notaPelanggan"},method = RequestMethod.POST)
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
        return "redirect:/notaPelanggan";
    }
    @RequestMapping(value = {"/pemesananSaya"})
    public String pemesananSaya(Model model,Authentication authentication){

        return "/customer/pemesananSaya";
    }
}
