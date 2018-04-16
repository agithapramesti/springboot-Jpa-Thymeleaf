package com.teknikinformatika.tugaspppl.service;

import com.teknikinformatika.tugaspppl.dao.fasilitas.FasilitasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FasilitasService {
    @Autowired
    FasilitasDao fasilitasDao;
}
