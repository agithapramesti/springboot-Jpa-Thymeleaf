package com.teknikinformatika.tugaspppl.dao.user;

import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer>, UserDaoCustom{
    @Query(value = "SELECT * from user where user.username = :username ", nativeQuery = true)
    User findByUsername(@Param("username") String username);
}
