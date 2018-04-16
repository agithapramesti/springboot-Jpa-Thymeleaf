package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.role.RoleDao;
import com.teknikinformatika.tugaspppl.dao.user.UserDao;
import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CabangDao cabangDao;
    @Autowired
    private RoleDao roleDao;

    public String save(Model model,User user){
        if(user.getRole().getRoleId()==0){

            user.getRole().setRoleId(8);
            user.setUsername(null);
            user.setStatusUser(1);
            user.setKataSandi("-");
        }
        userDao.save(user);
        return "redirect:/customerPage";
    }
    public String saveUserRegistered(Model model,User user){
        if(user.getRole().getRoleId()==0){
            user.getRole().setRoleId(7);
            user.setStatusUser(1);
        }
        userDao.save(user);
        return "redirect:/customerPage";
    }
    public Model getAllUsers(Model model){
        System.out.println("cek id="+userDao.getIdNext());
        model.addAttribute("users",userDao.findAllUsers());
        return model;
    }
    public Model searchUserByUsername(Model model, String username){
        model.addAttribute("users",userDao.getUserByUsername(username));
        return model;
    }
    public Model searchPegawaiByUsername(Model model, String username){
        model.addAttribute("users",userDao.getPegawaiByUsername(username));
        return model;
    }
    public String saveUser(Model model, User user){

        if(user.getStatusUser()==0){
            user.getRole().setRoleId(7);
            user.setStatusUser(1);
        }
        userDao.save(user);
        return "redirect:/dataPelanggan";
    }
    public Model manageTambahUser(Model model){
        model.addAttribute("users",new User());
        model.addAttribute("cabangs",cabangDao.getAllCabang());
        return model;
    }
    public Model manageEditPelangganPersonal(Model model, int id){
        model.addAttribute("users",userDao.findById(id));
        model.addAttribute("cabangs", cabangDao.getAllCabang());
        return model;
    }
    public String softDeleteUser(int id){
        userDao.softDeleteUser(id);
        return "redirect:/dataPegawai";
    }
    public String softDeletePelanggan(int id){
        userDao.softDeleteUser(id);
        return "redirect:/dataPelanggan";
    }
    public Model getAllPegawai(Model model){
        model.addAttribute("users",userDao.findAllPegawai());
        return model;
    }
    public Model manageTambahPegawai(Model model){
        model.addAttribute("users",new User());
        model.addAttribute("cabangs",cabangDao.getAllCabang());
        model.addAttribute("roles",roleDao.findAllRolesPegawai());
        return model;
    }
    public String savePegawai(Model model, User user){
        if(user.getStatusUser()==0){
            user.setNamaPemegangKartu("-");
            user.setNoKartu("-");
            user.setStatusUser(1);
        }
        userDao.save(user);
        return "redirect:/dataPegawai";
    }
    public Model manageEditPegawai(Model model, int id){
        model.addAttribute("users",userDao.findById(id));
        model.addAttribute("cabangs", cabangDao.getAllCabang());
        model.addAttribute("roles",roleDao.findAllRolesPegawai());
        return model;
    }
}
