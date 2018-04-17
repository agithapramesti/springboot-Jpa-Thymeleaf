package com.teknikinformatika.tugaspppl.controller;

import com.teknikinformatika.tugaspppl.model.User;
import com.teknikinformatika.tugaspppl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/"})
    public String mainPage() {

        return "home";
    }
    @RequestMapping(value = {"/home"})
    public String mainPageLogin() {

        return "home";
    }
    @RequestMapping("/login")
    public String login() {

        return "login";
    }
    @RequestMapping(value = {"/dataPelanggan"},method = RequestMethod.GET)
    public String dataPelanggan(Model model) {
        model=userService.getAllUsers(model);
        return "/admin/dataPelanggan";
    }
    @RequestMapping(value = {"/dataPelanggan"},method = RequestMethod.POST)
    public String simpanPelanggan(@ModelAttribute("users")User user, Model model) {
        return userService.saveUser(model,user);
    }
    @RequestMapping(value = "dataPelanggan/cari",method = RequestMethod.GET)
    public String cariBerdasarkanUsername(Model model, @ModelAttribute("username") String username){
        System.out.println("cek:"+username);
        model=userService.searchUserByUsername(model,username);
        return "/admin/dataPelanggan";
    }
    @RequestMapping(value = {"/dataPelanggan/tambahPelanggan"},method = RequestMethod.GET)
    public String manageTambahPelanggan(Model model) {
        model= userService.manageTambahUser(model);
        return "/admin/tambahPelanggan";
    }
    @RequestMapping(value = "/dataPelanggan/editPelanggan/{id}", method = RequestMethod.GET)
    public String editDataPelanggan(@PathVariable int id, Model model) {
        model = userService.manageEditPelangganPersonal(model,id);
        return "/admin/tambahPelanggan";
    }
    @RequestMapping(value = "dataPelanggan/softdelete/{id}",method = RequestMethod.GET)
    public String softDeleteDataPelanggan(@PathVariable int id){
        return userService.softDeletePelanggan(id);
    }
    @RequestMapping(value = {"/dataPegawai"},method = RequestMethod.GET)
    public String dataPegawai(Model model) {
        model=userService.getAllPegawai(model);
        return "/admin/dataPegawai";
    }
    @RequestMapping(value = {"/dataPegawai"},method = RequestMethod.POST)
    public String simpanDataPegawai(@ModelAttribute("users")User user,Model model) {
         return  userService.savePegawai(model, user);
    }
    @RequestMapping(value = {"/dataPegawai/tambahPegawai"},method = RequestMethod.GET)
    public String manageTambahPegawai(Model model){
        model=userService.manageTambahPegawai(model);
        return "/admin/tambahPegawai";
    }
    @RequestMapping(value = "/dataPegawai/editPegawai/{id}", method = RequestMethod.GET)
    public String editDataPegawai(@PathVariable int id, Model model) {
        model = userService.manageEditPegawai(model,id);
        return "/admin/tambahPegawai";
    }
    @RequestMapping(value = "dataPegawai/softdelete/{id}",method = RequestMethod.GET)
    public String softDeleteDataPegawai(@PathVariable int id){
        return userService.softDeleteUser(id);
    }
    @RequestMapping(value = "dataPegawai/cari",method = RequestMethod.GET)
    public String cariPegawaiByUsername(Model model, @ModelAttribute("username") String username){

        model=userService.searchPegawaiByUsername(model,username);
        return "/admin/dataPegawai";
    }
}

