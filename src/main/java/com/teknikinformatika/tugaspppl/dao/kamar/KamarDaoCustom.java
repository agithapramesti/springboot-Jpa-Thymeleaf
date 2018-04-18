package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.model.Kamar;

import java.util.List;

public interface KamarDaoCustom {
    Kamar softDelete(int id);
    int getStatusKamarById(int id);
    List<Kamar> getAllKamarsTersedia();
    List<Kamar> getAllKamarsTidakTersedia();
}
