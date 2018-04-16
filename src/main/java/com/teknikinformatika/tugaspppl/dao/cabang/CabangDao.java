package com.teknikinformatika.tugaspppl.dao.cabang;

import com.teknikinformatika.tugaspppl.model.Cabang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabangDao extends JpaRepository<Cabang,Integer>,CabangDaoCustom{

}
