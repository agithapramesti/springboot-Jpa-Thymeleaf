package com.teknikinformatika.tugaspppl.dao.season;

import com.teknikinformatika.tugaspppl.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonDao extends JpaRepository<Season, Integer>{
    Season findBySeasonId(int id);

    @Query(value = "SELECT * FROM season WHERE LOWER(season.nama_season) LIKE LOWER (%:nama%)",nativeQuery = true)
    List<Season> getAllSeasonByNamaSeason(@Param("nama") String nama);
    @Query(value = "select * from season join cabang on season.cabang_id=cabang.cabang_id WHERE lower(cabang.nama_cabang) like lower('%yogyakarta%') and status_season=1 and status_cabang=1", nativeQuery = true)
    List<Season> getAllSeasonYogyakarta();
    @Query(value= "select * from season join cabang on season.cabang_id=cabang.cabang_id WHERE lower(cabang.nama_cabang) like lower('%bandung%') and status_season=1 and status_cabang=1",nativeQuery = true)
    List<Season> getAllSeasonBandung();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE season set season.status_season = case when season.status_season=1 then 0 when season.status_season=0 then 1 end where season.season_id= :id ",nativeQuery = true)
    void softDelete(@Param("id") int id);
}
