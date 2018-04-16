package com.teknikinformatika.tugaspppl.dao.season;

import com.teknikinformatika.tugaspppl.model.Season;

import java.util.List;

public interface SeasonDaoCustom {
    Season softDeleteSeason(int id);
    List<Season> getAllAktifSeason();
    Season softDelete(int id);
    List<Season> getAllSeasonByNamaSeason(String nama);
    List<Season> getAllSeasonYogyakarta();
    List<Season> getAllSeasonBandung();
}
