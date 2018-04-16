package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisKamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisKamarDao extends JpaRepository<JenisKamar,Integer>{
}
