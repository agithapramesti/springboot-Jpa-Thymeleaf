package com.teknikinformatika.tugaspppl.dao.fasilitas_berbayar_id;

import com.teknikinformatika.tugaspppl.model.FasilitasBerbayar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasBerbayarDao extends JpaRepository<FasilitasBerbayar,Integer>{
}
