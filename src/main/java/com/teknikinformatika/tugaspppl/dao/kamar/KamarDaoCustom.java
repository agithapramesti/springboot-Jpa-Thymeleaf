package com.teknikinformatika.tugaspppl.dao.kamar;

import com.teknikinformatika.tugaspppl.model.Kamar;

public interface KamarDaoCustom {
    Kamar softDelete(int id);
    int getStatusKamarById(int id);
}
