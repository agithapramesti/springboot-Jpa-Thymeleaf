package com.teknikinformatika.tugaspppl.dao.season;

import com.teknikinformatika.tugaspppl.model.Season;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SeasonDaoImpl implements  SeasonDaoCustom{
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public Season softDeleteSeason(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Season result = em.find(Season.class, id);
        if(result.getStatuSeason()==1)
            result.setStatuSeason(0);
        else if(result.getStatuSeason()==0)
            result.setStatuSeason(1);
        em.getTransaction().commit();
        return result;
    }
    @Override
    public List<Season> getAllAktifSeason(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Season> result = em.createNativeQuery(
                "SELECT * FROM season WHERE STATUS=1", Season.class).getResultList();
        return result;
    }
    @Override
    public Season softDelete(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Season result = em.find(Season.class,id);
        if(result.getStatuSeason()==1)
            result.setStatuSeason(0);
        else if(result.getStatuSeason()==0)
            result.setStatuSeason(1);
        em.getTransaction().commit();
        return result;
    }

    @Override
    public List<Season> getAllSeasonByNamaSeason(String nama){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Season> result = em.createNativeQuery(
                "SELECT * FROM season WHERE LOWER(season.nama_season) LIKE LOWER ('%"+ nama +"%')", Season.class).getResultList();

        return result;
    }
    @Override
    public List<Season> getAllSeasonYogyakarta(){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Season> result = em.createNativeQuery(
                "select * from season join cabang on season.cabang_id=cabang.cabang_id WHERE lower(cabang.nama_cabang) like lower('%yogyakarta%') and status_season=1 and status_cabang=1", Season.class).getResultList();
        return result;
    }
    @Override
    public List<Season> getAllSeasonBandung(){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<Season> result = em.createNativeQuery(
                "select * from season join cabang on season.cabang_id=cabang.cabang_id WHERE lower(cabang.nama_cabang) like lower('%bandung%') and status_season=1 and status_cabang=1", Season.class).getResultList();
        return result;
    }
}
