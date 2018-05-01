package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservasiTemp {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private int jumlahKamar;
    private int jumlahAnak;
    private int jumlahDewasa;
    private Date tanggalCheckIn;
    private Date tanggalCheckOut;
    private double harga;
    private int cabangId;
    private int kamarId;
    private String kodeKamar;
    private String namaJenisKamar;

}
