package com.teknikinformatika.tugaspppl.dao.reservasi;

import com.teknikinformatika.tugaspppl.model.Reservasi;
import com.teknikinformatika.tugaspppl.model.ReservasiTemp;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
