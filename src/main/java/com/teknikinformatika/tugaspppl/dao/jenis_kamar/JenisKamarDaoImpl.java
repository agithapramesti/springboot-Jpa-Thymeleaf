package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisKamar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JenisKamarDaoImpl implements JenisKamarDaoCustom {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public JenisKamar softDelete(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        JenisKamar result = em.find(JenisKamar.class, id);
        if(result.getStatusJenis()==1)
            result.setStatusJenis(0);
        else if(result.getStatusJenis()==0)
            result.setStatusJenis(1);
        em.getTransaction().commit();
        return result;
    }
    @Override
    public List<JenisKamar> getAllJenisKamarActived(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<JenisKamar> result = em.createNativeQuery(
                "SELECT * FROM jenis_kamar WHERE jenis_kamar.status_jenis=1",JenisKamar.class).getResultList();
        return result;
    }
}
