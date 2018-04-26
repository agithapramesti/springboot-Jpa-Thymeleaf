package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisKamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JenisKamarDao extends JpaRepository<JenisKamar,Integer>    {
    @Query(value= "SELECT * FROM jenis_kamar WHERE jenis_kamar.status_jenis=1",nativeQuery = true)
    List<JenisKamar> getAllJenisKamarActived();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE jenis_kamar set jenis_kamar.status_jenis = case when jenis_kamar.status_jenis=1 then 0 when jenis_kamar.status_jenis=0 then 1 end where jenis_kamar.jenis_kamar_id= :id ",nativeQuery = true)
    void softDelete(@Param("id") int id);

}
