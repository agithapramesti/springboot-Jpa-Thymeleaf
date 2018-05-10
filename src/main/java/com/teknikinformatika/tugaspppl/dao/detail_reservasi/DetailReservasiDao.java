package com.teknikinformatika.tugaspppl.dao.detail_reservasi;

import com.teknikinformatika.tugaspppl.model.DetailReservasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface DetailReservasiDao extends JpaRepository<DetailReservasi,Integer>{
    @Modifying
    @Query(value = "INSERT INTO detail_reservasi (reservasi_id,jenis_kamar_id,kamar_id,tanggal_check_in,tanggal_check_out,sub_total_detail) VALUES(:reservasiId,:jenisKamarId,:kamarId,:tanggalCheckIn,:tanggalCheckOut,:subTotalDetail)", nativeQuery = true)
    @Transactional
    void saveToDetailReservasi(@Param("reservasiId") int reservasiId,
                               @Param("jenisKamarId") int jenisKamarId,
                               @Param("kamarId") int kamarId,
                               @Param("tanggalCheckIn") Date tanggalCheckIn,
                               @Param("tanggalCheckOut") Date tanggalCheckOut,
                               @Param("subTotalDetail") double subTotalDetail);
}
