package com.teknikinformatika.tugaspppl.dao.permintaan_khusus;

import com.teknikinformatika.tugaspppl.model.PermintaanKhusus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PermintaanKhususDao extends JpaRepository<PermintaanKhusus,Integer>{
    @Modifying
    @Query(value = "INSERT INTO permintaan_khusus (reservasi_id,fasilitas_berbayar_id,jumlah,item_price,subtotal_permintaan) VALUES(:reservasiId,:fasilitasBerbayarId,:jumlah,:itemPrice,:subtotalPermintaan)", nativeQuery = true)
    @Transactional
    void saveToPermintaanKhusus(@Param("reservasiId") int reservasiId,
                                @Param("fasilitasBerbayarId") int fasilitasBerbayarId,
                                @Param("jumlah") int jumlah,
                                @Param("itemPrice") double itemPrice,
                                @Param("subtotalPermintaan") double subtotalPermintaan);
}
