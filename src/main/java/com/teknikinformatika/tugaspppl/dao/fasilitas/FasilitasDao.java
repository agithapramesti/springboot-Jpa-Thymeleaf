package com.teknikinformatika.tugaspppl.dao.fasilitas;

import com.teknikinformatika.tugaspppl.model.Fasilitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasDao extends JpaRepository<Fasilitas,Integer>{
}
