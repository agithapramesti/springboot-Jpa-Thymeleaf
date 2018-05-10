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
}
