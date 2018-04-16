package com.teknikinformatika.tugaspppl.dao.pelanggan_grup;

import com.teknikinformatika.tugaspppl.model.PelangganGrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelanggaGrupDao extends JpaRepository<PelangganGrup,Integer>{
}
