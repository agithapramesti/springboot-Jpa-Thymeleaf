package com.teknikinformatika.tugaspppl.dao.user;

import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer>, UserDaoCustom{

}
