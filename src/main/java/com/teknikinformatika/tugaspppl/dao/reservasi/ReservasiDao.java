package com.teknikinformatika.tugaspppl.dao.reservasi;

import com.teknikinformatika.tugaspppl.model.Reservasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasiDao extends JpaRepository<Reservasi,Integer>{
}
