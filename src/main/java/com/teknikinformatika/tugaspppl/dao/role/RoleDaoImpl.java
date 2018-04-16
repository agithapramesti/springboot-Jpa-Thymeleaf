package com.teknikinformatika.tugaspppl.dao.role;

import com.teknikinformatika.tugaspppl.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class RoleDaoImpl implements RoleDaoCustom {

    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List<Role> findAllRolesPegawai(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Role> result = em.createNativeQuery(
                "SELECT * FROM role WHERE role_id!=7 and role_id!=8", Role.class).getResultList();
        return result;

    }
}
