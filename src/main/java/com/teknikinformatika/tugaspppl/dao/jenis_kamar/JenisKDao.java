package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JenisKDao extends JpaRepository<JenisK,Integer> {
    @Query(value = "SELECT j.jenis_kamar_id,j.nama_jenis_kamar,j.harga_jenis_kamar,j.kapasitas,j.gambar_kamar,count(k.kamar_id)-jt.stok AS 'stok' FROM kamar k join jenis_kamar j on k.jenis_kamar_id = j.jenis_kamar_id join jenis_kamar_temp jt ON jt.jenis_kamar_id=j.jenis_kamar_id where k.kamar_id not IN (SELECT dr.kamar_id from detail_reservasi dr WHERE :tanggalCheckIn BETWEEN dr.tanggal_check_in and dr.tanggal_check_out OR :tanggalCheckOut BETWEEN dr.tanggal_check_in and dr.tanggal_check_out) and k.status_kamar=1 and k.kamar_id IN (SELECT kc.kamar_id from kamar_cabang kc WHERE kc.cabang_id=:cabangId) and k.kamar_id NOT IN(SELECT rt.kamar_id from reservasi_temp rt) group by j.jenis_kamar_id",nativeQuery = true)
    List<JenisK> getAllKamarTersediaReservasi(@Param("tanggalCheckIn")Date tanggalCheckIn,
                                              @Param("tanggalCheckOut")Date tanggalCheckOut,
                                              @Param("cabangId")int cabangId);
}
