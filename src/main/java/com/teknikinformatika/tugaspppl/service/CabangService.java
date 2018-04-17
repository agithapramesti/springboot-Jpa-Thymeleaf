package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.cabang.CabangDao;
import com.teknikinformatika.tugaspppl.model.Cabang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CabangService {
    @Autowired
    CabangDao cabangDao;
    public List<Cabang> getAllCabang(){
        return cabangDao.getAllCabang();
    }
    public List<Cabang> getAllCabangByKamarId(int id){
        return cabangDao.getAllCabangByKamarId(id);
    }
}
