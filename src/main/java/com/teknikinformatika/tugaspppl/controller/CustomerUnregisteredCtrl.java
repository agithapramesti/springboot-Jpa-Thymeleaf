package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.User;
import com.teknikinformatika.tugaspppl.service.ReservasiService;
import com.teknikinformatika.tugaspppl.service.SeasonService;
import com.teknikinformatika.tugaspppl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerUnregisteredCtrl {
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private ReservasiService reservasiService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/customerPage"})
    public String mainPageCustomerTidakLogin(Model model) {
        model=reservasiService.manageFormCustomerPage(model);
        return "customer/customerPage";
    }
    @RequestMapping(value = {"/customerPage"},method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")User user, Model model) {
        return userService.save(model, user);

    }
    @RequestMapping("/loginAkun")
    public String loginAkun() {

        return "loginAkun";
    }
    @RequestMapping(value = {"/daftar"})
    public String daftarAkun(Model model) {
        model= userService.manageTambahUser(model);
        return "customer/daftar";
    }
    @RequestMapping(value = {"/daftar"},method = RequestMethod.POST)
    public String simpanPelanggan(@ModelAttribute("users")User user, Model model) {
        return userService.saveUserRegistered(model,user);
    }
}
