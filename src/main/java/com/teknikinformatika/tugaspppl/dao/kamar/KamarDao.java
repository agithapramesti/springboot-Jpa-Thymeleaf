package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.model.Kamar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KamarDao extends JpaRepository<Kamar,Integer>{
}
