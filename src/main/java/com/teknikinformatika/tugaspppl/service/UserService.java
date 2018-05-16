package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.dao.role.RoleDao;
import com.teknikinformatika.tugaspppl.dao.user.UserDao;
import com.teknikinformatika.tugaspppl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.Timestamp;

import static com.sun.deploy.trace.TraceLevel.SECURITY;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CabangDao cabangDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    protected AuthenticationManager authenticationManager;

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public String save(Model model,User user){

        if(userDao.countUserByNoIdentitas(user.getNoIdentitas())== 0){
            if(user.getRole().getRoleId()==0){
                int latestId = userDao.getLatestId()+1;
                user.getRole().setRoleId(8);
                user.setUsername(latestId+"normalUser");
                user.setStatusUser(1);
                user.setKataSandi(latestId+"normalUser");
                user.setTanggalUser(timestamp);

            }
            userDao.save(user);
            return "redirect:/reservasi";
        }
        else {
            System.out.println("#ERROR PADA INPUTAN USER ATAU NO IDENTITAS");
            return "redirect:/exceptionHandling";

        }

    }
    public void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getKataSandi();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());


    }
    public String saveUserRegistered(Model model,User user){

        if(userDao.countUserByUsername(user.getUsername()) ==0 && userDao.countUserByNoIdentitas(user.getNoIdentitas()) == 0) {
            if(user.getRole().getRoleId()==0){
                user.getRole().setRoleId(7);
                user.setStatusUser(1);
                user.setTanggalUser(timestamp);

            }
            userDao.save(user);
            return "redirect:/grandatmahotel";
        }
        else
        {
            System.out.println("#ERROR PADA INPUTAN USER ATAU NO IDENTITAS");
            return "redirect:/exceptionHandling";
        }

    }
    public Model getAllUsers(Model model){

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

        if(user.getUserId()==0){
            if(userDao.countUserByUsername(user.getUsername()) == 0 && userDao.countUserByNoIdentitas(user.getNoIdentitas()) == 0){
                user.getRole().setRoleId(7);
                user.setStatusUser(1);
                user.setTanggalUser(timestamp);
                userDao.save(user);

                return "redirect:/dataPelanggan";
            }
            else {
                return "redirect:/exceptionHandling";
            }
        }
        else{
            String oldUsername = userDao.getUsernameUserById(user.getUserId());
            int id =  user.getUserId();
            int statusUser = userDao.getStatusUserById(id);
            user.setStatusUser(statusUser);
            user.setTanggalUser(timestamp);
            if(oldUsername.equalsIgnoreCase(user.getUsername()))
            {

                userDao.save(user);
                return "redirect:/dataPelanggan";
            }
            else
            {
                if(userDao.countUserByUsername(user.getUsername()) == 0){
                    userDao.save(user);
                    return "redirect:/dataPelanggan";
                }
                else {
                    return "redirect:/exceptionHandling";
                }
            }

        }

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

        if(user.getUserId()==0){
            if(userDao.countUserByUsername(user.getUsername()) == 0 && userDao.countUserByNoIdentitas(user.getNoIdentitas()) == 0){
                user.setNamaPemegangKartu("-");
                user.setNoKartu("-");
                user.setStatusUser(1);
                userDao.save(user);
                return "redirect:/dataPegawai";
            }
            else {
                return "redirect:/exceptionHandling";
            }
        }
        else{
            String oldUsername = userDao.getUsernameUserById(user.getUserId());
            int id =  user.getUserId();
            int statusUser = userDao.getStatusUserById(id);
            user.setStatusUser(statusUser);
            if(oldUsername.equalsIgnoreCase(user.getUsername()))
            {
                userDao.save(user);
                return "redirect:/dataPegawai";
            }
            else
            {
                if(userDao.countUserByUsername(user.getUsername()) == 0){
                    userDao.save(user);
                    return "redirect:/dataPegawai";
                }
                else {
                    return "redirect:/exceptionHandling";
                }
            }

        }


    }
    public Model manageEditPegawai(Model model, int id){
        model.addAttribute("users",userDao.findById(id));
        model.addAttribute("cabangs", cabangDao.getAllCabang());
        model.addAttribute("roles",roleDao.findAllRolesPegawai());
        return model;
    }
    public Model kelolaDataPelanggan(Model model, Authentication authentication){
        int userId  = userDao.getIdByUsername(authentication.getName());
        String username = userDao.getUsernameUserById(userId);
        model.addAttribute("nama",userDao.getNamaUser(username));
        model.addAttribute("alamat",userDao.getAlamatUser(username));
        model.addAttribute("noTelp",userDao.getNoTelpUser(username));
        model.addAttribute("noIden",userDao.getNoIdentitasUser(username));
        model.addAttribute("email",userDao.getEmailUser(username));
        model.addAttribute("noKartu",userDao.getNoKartuUser(username));
        model.addAttribute("namaPemegang",userDao.getNamaPemegangUser(username));
        return model;
    }
    public Model manageUbahProfilAkun(Model model, Authentication authentication){
        int idUser = userDao.getIdByUsername(authentication.getName());
        model.addAttribute("users",userDao.findById(idUser));
        model.addAttribute("cabangs",cabangDao.getAllCabang());
        return model;
    }
    public String ubahProfil(Model model, User user){

        if(user.getUserId()==0){
            if(userDao.countUserByUsername(user.getUsername()) == 0 && userDao.countUserByNoIdentitas(user.getNoIdentitas()) == 0){
                user.getRole().setRoleId(7);
                user.setStatusUser(1);
                user.setTanggalUser(timestamp);
                userDao.save(user);

                return "redirect:/halamanPelanggan";
            }
            else {
                return "redirect:/exceptionHandling";
            }
        }
        else{
            String oldUsername = userDao.getUsernameUserById(user.getUserId());
            int id =  user.getUserId();
            int statusUser = userDao.getStatusUserById(id);
            user.setStatusUser(statusUser);
            user.setTanggalUser(timestamp);
            if(oldUsername.equalsIgnoreCase(user.getUsername()))
            {

                userDao.save(user);
                return "redirect:/halamanPelanggan";
            }
            else
            {
                if(userDao.countUserByUsername(user.getUsername()) == 0){
                    userDao.save(user);
                    return "redirect:/halamanPelanggan";
                }
                else {
                    return "redirect:/exceptionHandling";
                }
            }

        }

    }
}

