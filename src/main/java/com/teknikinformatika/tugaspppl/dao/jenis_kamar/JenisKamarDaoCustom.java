package com.teknikinformatika.tugaspppl.dao.jenis_kamar;

import com.teknikinformatika.tugaspppl.model.JenisKamar;

import java.util.List;

public interface JenisKamarDaoCustom {
    JenisKamar softDelete(int id);
    List<JenisKamar> getAllJenisKamarActived();
}
