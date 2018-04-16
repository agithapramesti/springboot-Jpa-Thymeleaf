package com.teknikinformatika.tugaspppl.dao.role;

import com.teknikinformatika.tugaspppl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer>, RoleDaoCustom{

}
