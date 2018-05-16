package com.teknikinformatika.tugaspppl.dao.reservasi;

import com.teknikinformatika.tugaspppl.model.Reservasi;
import com.teknikinformatika.tugaspppl.model.ReservasiTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservasiDao extends JpaRepository<Reservasi,Integer>{
    @Query(value = " SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'test01' AND TABLE_NAME = 'reservasi'", nativeQuery = true)
    int getIdReservasi();
    @Query(value = "SELECT COUNT(*) from reservasi_temp", nativeQuery = true)
    int countKamarFromCart();
    @Query(value = "SELECT jumlah_anak FROM reservasi_temp LIMIT 1", nativeQuery = true)
    int countJumlahAnak();
    @Query(value = "SELECT jumlah_dewasa FROM reservasi_temp LIMIT 1", nativeQuery = true)
    int countJumlahDewasa();
    @Query(value = "SELECT reservasi_temp.cabang_id FROM reservasi_temp LIMIT 1", nativeQuery = true)
    int getCabangId();
    @Query(value = "SELECT sum(jenis_kamar_temp.harga_kamar * jenis_kamar_temp.stok ) FROM jenis_kamar_temp", nativeQuery = true)
    double getTotalHargaKamarSebelum();
    @Query(value = "SELECT count(*) FROM reservasi ", nativeQuery = true)
    int getCount();
    @Query(value = "SELECT reservasi.kode_booking FROM reservasi DESC LIMIT 1 ", nativeQuery = true)
    String getKodeBookingLast();

    @Query(value = "SELECT DATE_FORMAT(:tanggal, '%d') ", nativeQuery = true)
    String tanggalReservasi(@Param("tanggal") Date tanggal);
    @Query(value = "SELECT DATE_FORMAT(:tanggal, '%m') ", nativeQuery = true)
    String bulanReservasi(@Param("tanggal") Date tanggal);
    @Query(value = "SELECT DATE_FORMAT(:tanggal, '%y') ", nativeQuery = true)
    String tahunReservasi(@Param("tanggal") Date tanggal);
    @Query(value = "SELECT sum(permintaan_khusus.subtotal_permintaan) FROM permintaan_khusus WHERE permintaan_khusus.reservasi_id= :id", nativeQuery = true)
    Double kalkukasiFasilitasBerbayar(@Param("id") int resId);
//    @Query(value = "SELECT count(reservasi_id) FROM permintaan_khusus WHERE reservasi_id=:id", nativeQuery = true)
//    int countFasilitasBerbayar(@Param("id") int id);
    @Query(value = "SELECT total_transaksi FROM reservasi WHERE reservasi_id=:id", nativeQuery = true)
    Double getTotalTransaksiById(@Param("id") int id);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE reservasi SET tax=:tax,total_transaksi=:totalTransaksi WHERE reservasi_id=:id",nativeQuery = true)
    void updateTotalTransaksi(@Param("id") int id,@Param("tax") double tax,@Param("totalTransaksi")double totalTransaksi);
    @Query(value = "SELECT sum(reservasi.total_transaksi) from reservasi WHERE reservasi.reservasi_id=:resId",nativeQuery = true)
    double getTotalTransaction(@Param("resId")int resId);
    @Query(value = "SELECT sum(IFNULL(SecondSet.Personal,0)+IFNULL(ThirdSet.Grup,0)) AS 'Total' FROM ( SELECT date_format(r.tanggal_reservasi,'%M') AS 'tanggal' FROM reservasi r GROUP BY date_format(r.tanggal_reservasi,'%M') ) AS FirstSet LEFT OUTER JOIN ( SELECT date_format(r.tanggal_reservasi,'%M') as 'tanggal', sum(r.total_transaksi) as \"Personal\" FROM reservasi r WHERE r.jenis_reservasi = 'P' GROUP by date_format(r.tanggal_reservasi,'%M') ) as SecondSet on FirstSet.tanggal = SecondSet.tanggal left outer JOIN ( SELECT date_format(r.tanggal_reservasi,'%M') as 'tanggal', sum(r.total_transaksi) as \"Grup\" FROM reservasi r WHERE r.jenis_reservasi = 'G' GROUP by date_format(r.tanggal_reservasi,'%M') )as ThirdSet on FirstSet.tanggal = ThirdSet.tanggal",nativeQuery = true)
    double getTotalPerulan();
    @Query(value = "SELECT COUNT(user.user_id) as \"jumlah\" FROM user WHERE YEAR(user.tanggal_user)=YEAR(CURRENT_DATE) and (user.role_id=7 or user.role_id=8)",nativeQuery = true)
    int getTotalCustomerBaru();
    @Query(value = "SELECT sum(IFNULL(SecondSet.jumlahOrang,0)+IFNULL(ThirdSet.jumlahOrang,0)) AS 'Total' \n" +
            "FROM \n" +
            "( \n" +
            "SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', jenis_kamar.nama_jenis_kamar as 'namaJenisKamar' FROM jenis_kamar \n" +
            ") AS FirstSet \n" +
            "LEFT OUTER JOIN \n" +
            "( SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', sum(reservasi.jumlah_orang) as 'jumlahOrang' FROM detail_reservasi JOIN reservasi ON(detail_reservasi.reservasi_id=reservasi.reservasi_id) JOIN jenis_kamar ON(detail_reservasi.jenis_kamar_id=jenis_kamar.jenis_kamar_id) WHERE detail_reservasi.reservasi_id IN (SELECT reservasi.reservasi_id FROM reservasi WHERE MONTH(reservasi.tanggal_reservasi)=MONTH(CURRENT_DATE) AND reservasi.jenis_reservasi='P') GROUP BY detail_reservasi.jenis_kamar_id \n" +
            ") as SecondSet \n" +
            "on FirstSet.jenisKamarId = SecondSet.jenisKamarId \n" +
            "left outer JOIN \n" +
            "( \n" +
            "SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', sum(reservasi.jumlah_orang) as 'jumlahOrang' FROM detail_reservasi JOIN reservasi ON(detail_reservasi.reservasi_id=reservasi.reservasi_id) JOIN jenis_kamar ON(detail_reservasi.jenis_kamar_id=jenis_kamar.jenis_kamar_id) WHERE detail_reservasi.reservasi_id IN (SELECT reservasi.reservasi_id FROM reservasi WHERE MONTH(reservasi.tanggal_reservasi)=MONTH(CURRENT_DATE) AND reservasi.jenis_reservasi='G') GROUP BY detail_reservasi.jenis_kamar_id \n" +
            ") as ThirdSet \n" +
            "on FirstSet.jenisKamarId = ThirdSet.jenisKamarId ",nativeQuery = true)
    int totalJumlahTamuJenisKamar();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE reservasi set reservasi.status_reservasi=:statusReservasi where reservasi.reservasi_id= :id",nativeQuery = true)
    void editReservasi(@Param("id") int id, @Param("statusReservasi") String statusReservasi);
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM reservasi where reservasi.reservasi_id= :id",nativeQuery = true)
    void batalReservasi(@Param("id") int id);
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM detail_reservasi where detail_reservasi.reservasi_id= :id",nativeQuery = true)
    void deleteDetailReservasi(@Param("id") int id);
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM permintaan_khusus where permintaan_khusus.reservasi_id= :id",nativeQuery = true)
    void deletePermintaanKhususReservasi(@Param("id") int id);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE detail_reservasi set detail_reservasi.tanggal_check_out=:tanggalCheckOut where detail_reservasi.reservasi_id= :reservasiId",nativeQuery = true)
    void tambahDurasi(@Param("reservasiId") int reservasiId, @Param("tanggalCheckOut") Date tanggalCheckOut);
    @Query(value = "SELECT * FROM reservasi WHERE reservasi.status_reservasi != 'check-out'",nativeQuery = true)
    List<Reservasi> getAllReservasisNotCheckOut();
    @Query(value = "SELECT * FROM reservasi WHERE reservasi.status_reservasi = 'check-out'",nativeQuery = true)
    List<Reservasi> getAllReservasisCheckOut();
    @Query(value = "SELECT YEAR(CURRENT_DATE)", nativeQuery = true)
    int getTahunIni();
    @Query(value = "SELECT sum(IFNULL(SecondSet.Total,0)+IFNULL(ThirdSet.Total,0)) AS 'Total' FROM ( SELECT YEAR(reservasi.tanggal_reservasi) AS 'Tahun' FROM reservasi GROUP BY YEAR(reservasi.tanggal_reservasi) ) AS FirstSet LEFT OUTER JOIN ( SELECT YEAR(reservasi.tanggal_reservasi) AS 'Tahun', SUM(reservasi.total_transaksi) AS 'Total' FROM reservasi WHERE cabang_id=1 GROUP BY YEAR(reservasi.tanggal_reservasi) ) as SecondSet on FirstSet.Tahun = SecondSet.Tahun left outer JOIN ( SELECT YEAR(reservasi.tanggal_reservasi) AS 'Tahun', SUM(reservasi.total_transaksi) AS 'Total' FROM reservasi WHERE cabang_id=2 GROUP BY YEAR(reservasi.tanggal_reservasi) )as ThirdSet on FirstSet.Tahun = ThirdSet.Tahun",nativeQuery = true)
    double totalPendapatanCabangTahunan();
    @Query(value = "SELECT DATE_FORMAT(CURRENT_DATE, '%d %M %Y' )",nativeQuery = true)
    String getTahun();
}
