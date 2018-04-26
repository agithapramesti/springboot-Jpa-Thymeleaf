package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.model.Kamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KamarDao extends JpaRepository<Kamar,Integer>{
    @Query(value = "SELECT count(*) FROM kamar_cabang where kamar_cabang.kamar_id=:id ", nativeQuery = true)
    int countCabang(@Param("id")Integer id);
    @Query(value = "SELECT kamar.status_kamar FROM kamar WHERE kamar.kamar_id=:id ", nativeQuery = true)
    int getStatusKamarById(@Param("id")Integer id);

    @Query(value = "SELECT * FROM kamar where kamar.kamar_id not IN (SELECT detail_reservasi.kamar_id from detail_reservasi where sysdate() BETWEEN detail_reservasi.tanggal_check_in and detail_reservasi.tanggal_check_out )", nativeQuery = true)
    List<Kamar> getAllKamarsTersedia();
    @Query(value = "SELECT * FROM kamar where kamar.kamar_id IN (SELECT detail_reservasi.kamar_id from detail_reservasi where sysdate() BETWEEN detail_reservasi.tanggal_check_in and detail_reservasi.tanggal_check_out )", nativeQuery = true)
    List<Kamar> getAllKamarsTidakTersedia();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE kamar set kamar.status_kamar = case when kamar.status_kamar=1 then 0 when kamar.status_kamar=0 then 1 end where kamar.kamar_id= :id ",nativeQuery = true)
    void softDelete(@Param("id") int id);
}
