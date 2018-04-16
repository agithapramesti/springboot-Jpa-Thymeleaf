package com.teknikinformatika.tugaspppl.dao.permintaan_khusus;

import com.teknikinformatika.tugaspppl.model.PermintaanKhusus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermintaanKhususDao extends JpaRepository<PermintaanKhusus,Integer>{
}
