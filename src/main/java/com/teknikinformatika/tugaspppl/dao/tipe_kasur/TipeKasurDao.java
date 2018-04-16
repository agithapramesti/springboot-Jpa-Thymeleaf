package com.teknikinformatika.tugaspppl.dao.tipe_kasur;

import com.teknikinformatika.tugaspppl.model.TipeKasur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeKasurDao extends JpaRepository<TipeKasur,Integer>{
}
