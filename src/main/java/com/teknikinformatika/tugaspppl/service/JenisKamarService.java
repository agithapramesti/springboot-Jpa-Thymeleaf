package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.jenis_kamar.JenisKamarDao;
import com.teknikinformatika.tugaspppl.model.JenisKamar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisKamarService {
    @Autowired
    JenisKamarDao jenisKamarDao;
    public JenisKamar save(JenisKamar jenisKamar){
        return jenisKamarDao.save(jenisKamar);
    }

}
