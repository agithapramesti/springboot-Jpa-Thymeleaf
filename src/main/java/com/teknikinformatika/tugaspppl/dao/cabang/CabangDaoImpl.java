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
}
