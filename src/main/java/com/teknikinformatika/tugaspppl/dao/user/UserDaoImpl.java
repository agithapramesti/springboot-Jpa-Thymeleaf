package com.teknikinformatika.tugaspppl.dao.user;


import com.teknikinformatika.tugaspppl.dao.My_Connection;
import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDaoImpl extends My_Connection implements UserDaoCustom {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List<User> getUserByUsername(String username){

        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> result = em.createNativeQuery(
                "select * from user WHERE role_id=7 AND lower(user.username) like lower('%"+ username +"%') ", User.class).getResultList();
        return result;
    }
    @Override
    public List<User> getPegawaiByUsername(String username){
        System.out.println("user:"+username);
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> result = em.createNativeQuery(
                "select * from user WHERE role_id!=7 and role_id!=8 AND lower(user.username) like lower('%"+ username +"%') ", User.class).getResultList();
        return result;
    }
    @Override
    public List<User> findAllUsers(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> result = em.createNativeQuery(
                "SELECT * FROM `user` WHERE role_id=7  order by user.nama ASC", User.class).getResultList();
        return result;
    }
    @Override
    public User softDeleteUser(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User result = em.find(User.class,id);
        if(result.getStatusUser()==1)
            result.setStatusUser(0);
        else if(result.getStatusUser()==0)
            result.setStatusUser(1);
        em.getTransaction().commit();
        return result;
    }
    @Override
    public List<User> findAllPegawai(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> result = em.createNativeQuery(
                "SELECT * FROM `user`  where user.role_id!=7 and user.role_id!=8", User.class).getResultList();
        return result;
    }
    @Autowired
    public int getIdNext(){
        System.out.println("YEEEEEE MASUQ");
        String psql=" SELECT AUTO_INCREMENT\n" +
                "FROM information_schema.TABLES\n" +
                "WHERE TABLE_SCHEMA = \"test01\"\n" +
                "AND TABLE_NAME = \"user\"";
        int idTemp=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    idTemp = resultSet.getInt("auto_increment");
                }
            }
            System.out.println("yeyeyey");
            System.out.println("ini:"+idTemp);
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get nextval.."+e.toString());
        }
       return idTemp;
    }
}
