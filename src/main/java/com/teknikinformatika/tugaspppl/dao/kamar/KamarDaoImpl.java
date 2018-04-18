package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.dao.My_Connection;
import com.teknikinformatika.tugaspppl.model.Kamar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class KamarDaoImpl extends My_Connection implements KamarDaoCustom  {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public Kamar softDelete(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Kamar result = em.find(Kamar.class,id);
        if(result.getStatusKamar()==1)
            result.setStatusKamar(0);
        else if(result.getStatusKamar()==0)
            result.setStatusKamar(1);
        em.getTransaction().commit();
        return result;
    }
    @Override
    public int getStatusKamarById(int id){
        String psql="SELECT kamar.status_kamar FROM kamar WHERE kamar.kamar_id='"+id+"'";
        int idTemp=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    idTemp = resultSet.getInt("status_kamar");
                }
            }


            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get nextval.."+e.toString());
        }
        return idTemp;
    }
    @Override
    public List<Kamar> getAllKamarsTersedia(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Kamar> result = em.createNativeQuery(
                "SELECT * FROM kamar where kamar.kamar_id not IN (SELECT detail_reservasi.kamar_id from detail_reservasi)",Kamar.class).getResultList();
        return result;
    }
    @Override
    public List<Kamar> getAllKamarsTidakTersedia(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Kamar> result = em.createNativeQuery(
                "SELECT * FROM kamar where kamar.kamar_id IN (SELECT detail_reservasi.kamar_id from detail_reservasi)",Kamar.class).getResultList();
        return result;
    }
}
