package com.teknikinformatika.tugaspppl.dao.cabang;

import com.teknikinformatika.tugaspppl.model.Cabang;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CabangDaoImpl implements CabangDaoCustom {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List<Cabang> getAllCabang(){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Cabang> result = em.createNativeQuery(
                "SELECT * from cabang WHERE status_cabang=1 ",Cabang.class).getResultList();
        return result;
    }
    @Override
    public List<Cabang> getAllCabangNotIncludedByKamarId(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Cabang> result = em.createNativeQuery(
                "SELECT * FROM cabang WHERE cabang_id NOT IN (SELECT c.cabang_id FROM kamar_cabang c JOIN kamar k ON(k.kamar_id=c.kamar_id) WHERE c.kamar_id='"+id+"') ",Cabang.class).getResultList();
        return result;
    }
}
