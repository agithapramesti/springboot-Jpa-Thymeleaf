package com.teknikinformatika.tugaspppl.dao.user;

import com.teknikinformatika.tugaspppl.model.User;

import java.util.List;

public interface UserDaoCustom {
    List<User> getUserByUsername(String username);
    List<User> getPegawaiByUsername(String username);
    List<User> findAllUsers();
    User softDeleteUser(int id);
    List<User> findAllPegawai();
    int getIdNext();
}
