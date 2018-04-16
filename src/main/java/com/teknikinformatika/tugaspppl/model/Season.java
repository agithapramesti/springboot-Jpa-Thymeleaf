package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "season")
public class Season {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "season_id")
    private int seasonId;
    @Column(name = "status_season", nullable = false)
    private int statuSeason;
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggalMulai;
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggalSelesai;
    @Column(name = "nama_season", nullable = false)
    private String namaSeason;
    @Column(name = "promo_season", nullable = false)
    private double promoSeason;

    @ManyToOne
    @JoinColumn(name = "cabang_id",insertable=true, updatable=true)
    public Cabang cabangs;

  //  private int cabangId;
}
