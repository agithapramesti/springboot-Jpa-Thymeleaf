package com.teknikinformatika.tugaspppl.dao.season;

import com.teknikinformatika.tugaspppl.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonDao extends JpaRepository<Season, Integer>,SeasonDaoCustom{
    Season findBySeasonId(int id);
}
