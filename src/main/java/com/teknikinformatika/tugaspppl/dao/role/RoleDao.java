package com.teknikinformatika.tugaspppl.dao.role;

import com.teknikinformatika.tugaspppl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer>{
    @Query(value="SELECT * FROM role WHERE role_id!=7 and role_id!=8" ,nativeQuery = true)
    List<Role> findAllRolesPegawai();
}
