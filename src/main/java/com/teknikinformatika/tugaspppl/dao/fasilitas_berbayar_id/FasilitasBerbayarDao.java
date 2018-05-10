package com.teknikinformatika.tugaspppl.dao.fasilitas_berbayar_id;

import com.teknikinformatika.tugaspppl.model.FasilitasBerbayar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasBerbayarDao extends JpaRepository<FasilitasBerbayar,Integer>{
    @Query(value = "SELECT harga_fasilitas FROM `fasilitas_berbayar` WHERE fasilitas_berbayar_id= :id",nativeQuery = true)
    double getHargaFasilitasById(@Param("id") int id);
}
