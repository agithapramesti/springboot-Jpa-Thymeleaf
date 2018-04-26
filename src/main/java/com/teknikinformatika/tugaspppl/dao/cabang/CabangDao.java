package com.teknikinformatika.tugaspppl.dao.cabang;

import com.teknikinformatika.tugaspppl.model.Cabang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabangDao extends JpaRepository<Cabang,Integer>{
    @Query(value = "SELECT count(*) as cabang FROM cabang WHERE cabang_id NOT IN (SELECT cabang_id FROM kamar_cabang WHERE kamar_id= :id ", nativeQuery = true)
    int countCabangKamarId(@Param("id") Integer id);
    @Query(value= "SELECT * from cabang WHERE status_cabang=1 ",nativeQuery = true)
    List<Cabang> getAllCabang();
    @Query(value= "SELECT * FROM cabang WHERE cabang_id NOT IN (SELECT cabang_id FROM kamar_cabang  WHERE kamar_id=:id) ",nativeQuery = true)
    List<Cabang> getAllCabangNotIncludedByKamarId(@Param("id") int id);
    @Query(value= "select * from cabang where cabang.cabang_id IN (SELECT kamar_cabang.cabang_id FROM kamar_cabang WHERE kamar_cabang.kamar_id=:id)",nativeQuery = true)
    List<Cabang> getAllCabangByKamarId(@Param("id") int id);
}
