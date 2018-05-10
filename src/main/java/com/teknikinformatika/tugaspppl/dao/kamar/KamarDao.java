package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.model.JenisK;
import com.teknikinformatika.tugaspppl.model.Kamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KamarDao extends JpaRepository<Kamar,Integer>{
    @Query(value = "SELECT count(*) FROM kamar_cabang where kamar_cabang.kamar_id=:id ", nativeQuery = true)
    int countCabang(@Param("id")Integer id);
    @Query(value = "SELECT kamar.status_kamar FROM kamar WHERE kamar.kamar_id=:id ", nativeQuery = true)
    int getStatusKamarById(@Param("id")Integer id);

    @Query(value = "SELECT * FROM kamar WHERE kamar.kamar_id not in (SELECT detail_reservasi.kamar_id " +
            "FROM detail_reservasi where sysdate() BETWEEN detail_reservasi.tanggal_check_in " +
            "and detail_reservasi.tanggal_check_out)", nativeQuery = true)
    List<Kamar> getAllKamarsTersedia();
    @Query(value = "SELECT * FROM kamar WHERE kamar.kamar_id  in " +
            "(SELECT detail_reservasi.kamar_id FROM detail_reservasi where sysdate() " +
            "BETWEEN detail_reservasi.tanggal_check_in and detail_reservasi.tanggal_check_out)",
            nativeQuery = true)
    List<Kamar> getAllKamarsTidakTersedia();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE kamar set kamar.status_kamar = case when kamar.status_kamar=1 then 0 when kamar.status_kamar=0 then 1 end where kamar.kamar_id= :id ",nativeQuery = true)
    void softDelete(@Param("id") int id);
    @Query(value = "SELECT * FROM kamar WHERE jenis_kamar_id=:jenisKamarId AND kamar_id NOT IN (SELECT dr.kamar_id from detail_reservasi dr WHERE :tanggalCheckIn BETWEEN dr.tanggal_check_in and dr.tanggal_check_out OR :tanggalCheckOut BETWEEN dr.tanggal_check_in and dr.tanggal_check_out) LIMIT :limit", nativeQuery = true)
    List<Kamar> getSomeKamarTersedia(@Param("jenisKamarId") int jenisKamarId, @Param("limit") int limit,
                                     @Param("tanggalCheckIn") Date tanggalCheckIn,
                                     @Param("tanggalCheckOut") Date tanggalCheckOut);


}
