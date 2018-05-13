package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.detail_reservasi.DetailReservasiDao;
import com.teknikinformatika.tugaspppl.dao.fasilitas_berbayar_id.FasilitasBerbayarDao;
import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarDao;
import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKDao;
import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarTempDao;
import com.teknikinformatika.tugaspppl.dao.kamar.KamarDao;
import com.teknikinformatika.tugaspppl.dao.permintaan_khusus.PermintaanKhususDao;
import com.teknikinformatika.tugaspppl.dao.reservasi.ReservasiDao;
import com.teknikinformatika.tugaspppl.dao.reservasi.ReservasiTempDao;
import com.teknikinformatika.tugaspppl.dao.season.SeasonDao;
import com.teknikinformatika.tugaspppl.dao.user.UserDao;
import com.teknikinformatika.tugaspppl.model.*;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservasiService {
    @Autowired
    private JenisKDao jenisKDao;
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
    private DetailReservasiDao detailReservasiDao;
    @Autowired
    private ReservasiDao reservasiDao;
    @Autowired
    private JenisKamarTempDao jenisKamarTempDao;
    @Autowired
    private FasilitasBerbayarDao fasilitasBerbayarDao;
    @Autowired
    private PermintaanKhususDao permintaanKhususDao;

    private Reservasi reservasi;
    private JenisKamarTemp jenisKamarTemp ;
    private DetailReservasi detailReservasi;

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

        model.addAttribute("kamar",jenisKDao.getAllKamarTersediaReservasi(GlobalVariable.tanggalCheckIn,GlobalVariable.tanggalCheckOut,GlobalVariable.cabangId));
        model.addAttribute("kamarTemp",jenisKamarTempDao.findAll());
        System.out.println(GlobalVariable.tanggalCheckIn);
        System.out.println(GlobalVariable.tanggalCheckOut);
        System.out.println(GlobalVariable.cabangId);
        int jumlahOrang = GlobalVariable.jumlahAnak + GlobalVariable.jumlahDewasa;
        int jumlahKamar;
        if((jumlahOrang%2) == 0){
            jumlahKamar=  (jumlahOrang/2);
        }
        else {
            jumlahKamar = (jumlahOrang/2)+1;
        }
        model.addAttribute("jumlahKamar", jumlahKamar);
        model.addAttribute("totalStok",jenisKamarTempDao.sumStokFromJenisKamarTemp());
        return model;
    }
    public Model manageMoveToCart(int id, Model model, int quantity){
        JenisKamarTemp jenisKamarTemp =  new JenisKamarTemp();
        jenisKamarTemp.setJenisKamarId(id);
        jenisKamarTemp.setStok(quantity);
        jenisKamarTemp.setNamaJenisKamar(jenisKamarDao.namaJenisKamarById(id));
        double harga;
        harga = jenisKamarDao.hargaJenisKamarById(id) * quantity;
        jenisKamarTemp.setHargaKamar(harga);
        jenisKamarTempDao.save(jenisKamarTemp);
        return model;
    }
    public void hapusCartById(int id){
        jenisKamarTempDao.deleteJenisKamarTempById(id);
    }
    public void hapusAllCart(){
        jenisKamarTempDao.deleteAllJenisKamarTemp();
    }
    public Model manageReservasiAndDetails(Authentication authentication,Model model){
        String namaKota;
        if(GlobalVariable.cabangId ==1){
            namaKota = "Yogyakarta";
        }
        else{
            namaKota = "Bandung";
        }
        model.addAttribute("kotaCabang",namaKota);
        model.addAttribute("tanggalCheckIn",new SimpleDateFormat("EEEE, dd MMMM yyyy").format(GlobalVariable.tanggalCheckIn));
        model.addAttribute("tanggalCheckOut",new SimpleDateFormat("EEEE, dd MMMM yyyy").format(GlobalVariable.tanggalCheckOut));
        long startTime = GlobalVariable.tanggalCheckIn.getTime();
        long endTime = GlobalVariable.tanggalCheckOut.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        GlobalVariable.durasiTemp = (int)diffDays;
        model.addAttribute("diffDays",diffDays);
        int jumlahOrang = GlobalVariable.jumlahAnak + GlobalVariable.jumlahDewasa;

        model.addAttribute("jumlahTamu",jumlahOrang);
        int jumlahKamar;
        if((jumlahOrang%2) == 0){
            jumlahKamar=  (jumlahOrang/2);
        }
        else {
            jumlahKamar = (jumlahOrang/2)+1;
        }
        model.addAttribute("jumlahKamar", jumlahKamar);


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //--------> Insert Reservasi
        reservasi = new Reservasi();
        reservasi.setTanggalReservasi(timestamp);
        reservasi.setJenisReservasi('P');
        reservasi.setTotalTransaksi(reservasiDao.getTotalHargaKamarSebelum());
        reservasi.setTotalDeposit(0);
        reservasi.setTax(0);
        reservasi.setDp(0);
        reservasi.setSisaDeposit(0);
        reservasi.setSisaPembayaran(0);
        reservasi.setJumlahKamar(jumlahKamar);
        reservasi.setJumlahDewasa(GlobalVariable.jumlahDewasa);
        reservasi.setJumlahAnak(GlobalVariable.jumlahAnak);
        reservasi.setJumlahOrang(jumlahOrang);
        reservasi.setStatusReservasi("on-process");
        reservasi.setCabang_res(cabangDao.getOne(GlobalVariable.cabangId));
        reservasi.setUsers(userDao.getOne(userDao.getIdByUsername(authentication.getName())));
        String kodeBooking="", tanggalRes, bulanRes, tahunRes;
        tanggalRes = reservasiDao.tanggalReservasi(timestamp);
        bulanRes = reservasiDao.bulanReservasi(timestamp);
        tahunRes = reservasiDao.tahunReservasi(timestamp);
        int reservasiId = reservasiDao.getIdReservasi();
        GlobalVariable.resId = reservasiId;
        kodeBooking = "P"+tanggalRes+bulanRes+tahunRes+"-"+"0"+reservasiId;
        reservasi.setKodeBooking(kodeBooking);
        reservasiDao.save(reservasi);

        return model;
    }

    public Model manageReservasiFasilitasBerbayar(Model model,int extraBed,int laundryRegularPotong,
                                                  int laundryFastServicePotong, int massageOrang,
                                                  int minibarMinuman, int tambahanBreakfastOrang,
                                                  int lunchPackageOrang, int dinnerPackageOrang,
                                                  int meetingRoomFullDayOrang){

        int[] quantityFasilitasBerbayar = new int[9];
        quantityFasilitasBerbayar[0] = extraBed;
        quantityFasilitasBerbayar[1] = laundryRegularPotong;
        quantityFasilitasBerbayar[2] = laundryFastServicePotong;
        quantityFasilitasBerbayar[3] = massageOrang;
        quantityFasilitasBerbayar[4] = minibarMinuman;
        quantityFasilitasBerbayar[5] = tambahanBreakfastOrang;
        quantityFasilitasBerbayar[6] = lunchPackageOrang;
        quantityFasilitasBerbayar[7] = dinnerPackageOrang;
        quantityFasilitasBerbayar[8] = meetingRoomFullDayOrang;
        double harga;
        double subtotal;
        for(int i=0;i<9;i++)
        {
            if(quantityFasilitasBerbayar[i]>0)
            {
                harga = fasilitasBerbayarDao.getHargaFasilitasById(i+1);
                subtotal = quantityFasilitasBerbayar[i] * harga;

                permintaanKhususDao.saveToPermintaanKhusus(GlobalVariable.resId,i+1,quantityFasilitasBerbayar[i],harga,subtotal);
            }
        }
        return model;
    }

    public Model manageDetailReservasi(Model model){

        List<JenisKamarTemp> jenisKamarTemps = jenisKamarTempDao.findAll();


        for (JenisKamarTemp jenisKamarTemp : jenisKamarTemps)
        {
            if(jenisKamarTemp.getStok()>0)
            {
                List<Kamar> kamarTemps = kamarDao.getSomeKamarTersedia(jenisKamarTemp.getJenisKamarId(),jenisKamarTemp.getStok(),GlobalVariable.tanggalCheckIn,GlobalVariable.tanggalCheckOut);
                for (Kamar kamarTemp : kamarTemps)
                {
                    detailReservasiDao.saveToDetailReservasi(GlobalVariable.resId,jenisKamarTemp.getJenisKamarId(),kamarTemp.getKamarId(),GlobalVariable.tanggalCheckIn,GlobalVariable.tanggalCheckOut,jenisKamarTemp.getHargaKamar()/jenisKamarTemp.getStok());
                }
            }
        }
        return model;
    }

    public Model manageFormKelolaReservasi(Model model){

        model.addAttribute("reservasi", reservasiDao.getAllReservasisNotCheckOut());
        model.addAttribute("reservasii", reservasiDao.findAll());
        model.addAttribute("search", new SearchTambahDurasi());
        return model;
    }

    public Model manageFormHistoriReservasi(Model model){

        model.addAttribute("reservasi", reservasiDao.getAllReservasisCheckOut());

        return model;
    }

    public String manageFormEditReservasi(int reservasiId,Model model){
        String statusReservasi="";
        Reservasi reservasi = new Reservasi();
        reservasi = reservasiDao.getOne(reservasiId);
        statusReservasi = reservasi.getStatusReservasi();
        if(statusReservasi.equalsIgnoreCase("on-process"))
        {
            reservasiDao.editReservasi(reservasiId,"check-in");
        }
        else if(statusReservasi.equalsIgnoreCase("check-in"))
        {
            reservasiDao.editReservasi(reservasiId,"check-out");
        }

        return "redirect:/kelolaReservasi";
    }

    public String manageFormBatalReservasi(int reservasiId,Model model){

        reservasiDao.batalReservasi(reservasiId);
        reservasiDao.deleteDetailReservasi(reservasiId);
        reservasiDao.deletePermintaanKhususReservasi(reservasiId);

        return "redirect:/kelolaReservasi";
    }

    public String manageTambahDurasi(SearchTambahDurasi searchTambahDurasi,Model model){
        int id = searchTambahDurasi.getReservasiId();
        int tambahDurasi = searchTambahDurasi.getDurasi();
        Calendar c = Calendar.getInstance();
        Reservasi reservasi = new Reservasi();
        reservasi = reservasiDao.getOne(id);
        Date tanggalCheckOut = reservasi.getDetailReservasis().get(0).getTanggalCheckOut();
        c.setTime(tanggalCheckOut);
        c.add(Calendar.DATE,tambahDurasi);
        Date newTanggalCheckOut = c.getTime();
        reservasiDao.tambahDurasi(id,newTanggalCheckOut);
        return "redirect:/kelolaReservasi";
    }
    public void kalkulasiFasilitasBerbayar(){

        Double fasilitasBerbayar = reservasiDao.kalkukasiFasilitasBerbayar(GlobalVariable.resId);
        System.out.println("1");
        if(fasilitasBerbayar==null)
        {
            fasilitasBerbayar=0.0;
            System.out.println("2");
        }
        GlobalVariable.kalkulasiFasilitasBerbayar = fasilitasBerbayar;
        System.out.println("1:"+fasilitasBerbayar);
    }
    public void kalkulasiTotalPembayaran(){
        Double season = seasonDao.getSeasonByTanggalAndCabang(GlobalVariable.tanggalCheckIn,GlobalVariable.cabangId);
        if(season == null || season==0)
        {
            season=100.0;
        }
        Double tax = 10.0;
        Double totalTransaksi = GlobalVariable.durasiTemp * (reservasiDao.getTotalTransaksiById(GlobalVariable.resId) + GlobalVariable.kalkulasiFasilitasBerbayar) * (season/100) * ((100+tax)/100);
        reservasiDao.updateTotalTransaksi(GlobalVariable.resId,tax,totalTransaksi);
    }
    public Model manageReservasiNota(Model model,Authentication authentication){
        Reservasi r = new Reservasi();
        int userId = userDao.getIdByUsername(authentication.getName());
        r = reservasiDao.getOne(GlobalVariable.resId);
        DetailReservasi detailReservasi = new DetailReservasi();


        model.addAttribute("kodeBooking",r.getKodeBooking());
        model.addAttribute("details",detailReservasiDao.getAllDetailByResId(GlobalVariable.resId));
        model.addAttribute("total", reservasiDao.getTotalTransaction(GlobalVariable.resId));
        return model;
    }
}
