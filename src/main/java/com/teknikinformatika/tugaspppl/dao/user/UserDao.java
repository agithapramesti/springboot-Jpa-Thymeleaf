package com.teknikinformatika.tugaspppl.dao.user;

import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer>{
    @Query(value = "SELECT count(*) from user where lower(user.username) = LOWER(:username) ", nativeQuery = true)
    int countUserByUsername(@Param("username") String username);
    @Query(value = "SELECT count(*) from user where LOWER(user.no_identitas) = LOWER(:noIdentitas) ", nativeQuery = true)
    int countUserByNoIdentitas(@Param("noIdentitas") String noIdentitas);
    @Query(value = "SELECT user.status_user FROM user WHERE user.user_id= :id ", nativeQuery = true)
    int getStatusUserById(@Param("id") Integer id);
    @Query(value = "SELECT * FROM `user` WHERE role_id=7  order by user.nama ASC", nativeQuery = true)
    List<User> findAllUsers();
    @Query(value = "select * from user WHERE role_id!=7 and role_id!=8 AND lower(user.username) like lower(%:username%) ",nativeQuery = true)
    List<User> getPegawaiByUsername(@Param("username") String username);
    @Query(value = "SELECT * FROM user  where user.role_id!=7 and user.role_id!=8",nativeQuery = true)
    List<User> findAllPegawai();
    @Query(value = "select * from user WHERE role_id=7 AND lower(user.username) like lower(%:username%)",nativeQuery = true)
    List<User> getUserByUsername(@Param("username") String username);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user set user.status_user = case when user.status_user=1 then 0 when user.status_user=0 then 1 end where user.user_id= :id ",nativeQuery = true)
    void softDeleteUser(@Param("id") int id);
    @Query(value = "SELECT user.username FROM user WHERE user.user_id= :id ", nativeQuery = true)
    String getUsernameUserById(@Param("id") Integer id);
    @Query(value = "SELECT user.user_id FROM user WHERE user.username= :username ", nativeQuery = true)
    int getIdByUsername(@Param("username") String username);
    @Query(value = "SELECT user.user_id,user.email,user.kata_sandi,user.nama,user.nama_pemegang_kartu,user.no_identitas,user.no_kartu,user.no_telp,user.status_user,user.username,user.cabang_id=:cabangId,user.role_id=:roleId,user.alamat,user.tanggal_user from user  JOIN reservasi ON user.user_id = reservasi.user_id WHERE reservasi.user_id=:userId LIMIT 1",nativeQuery = true)
    User getUserByResId(@Param("userId")int userId,@Param("cabangId")int cabangId, @Param("roleId")int roleId);
}
