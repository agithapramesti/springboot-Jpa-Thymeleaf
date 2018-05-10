package com.teknikinformatika.tugaspppl.dao.reservasi;

import com.teknikinformatika.tugaspppl.model.ReservasiTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservasiTempDao extends JpaRepository<ReservasiTemp,Integer> {
    @Modifying
    @Query(value = "delete from reservasi_temp WHERE reservasi_temp.id=:id",nativeQuery = true)
    void hapusCart(@Param("id")int id);

}
