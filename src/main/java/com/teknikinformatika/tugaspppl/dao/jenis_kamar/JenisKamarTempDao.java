package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisKamarTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JenisKamarTempDao extends JpaRepository<JenisKamarTemp,Integer>{
    @Modifying
    @Query(value = "UPDATE jenis_kamar_temp SET jenis_kamar_temp.stok=0",nativeQuery = true)
    void deleteAllJenisKamarTemp();
    @Modifying
    @Query(value = "UPDATE jenis_kamar_temp SET jenis_kamar_temp.stok=0 WHERE jenis_kamar_temp.jenis_kamar_id=:id",nativeQuery = true)
    void deleteJenisKamarTempById(@Param("id")int id);
    @Query(value = "SELECT sum(jenis_kamar_temp.stok) FROM jenis_kamar_temp",nativeQuery = true)
    int sumStokFromJenisKamarTemp();
}
