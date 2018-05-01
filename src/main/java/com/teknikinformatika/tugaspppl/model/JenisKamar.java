package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jenis_kamar")
public class JenisKamar {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "jenis_kamar_id")
    private int jenisKamarId;
    @Column(name = "kapasitas", nullable = false)
    private int kapasitas;
    @Column(name = "status_jenis", nullable = false)
    private int statusJenis;
    @Column(name = "harga_jenis_kamar", nullable = false)
    private double hargaJenisKamar;
    @Column(name = "nama_jenis_kamar", nullable = false)
    @Size(max = 255)
    private String namaJenisKamar;
    @Column(name = "gambar_kamar")
    @Size(max = 255)
    private String gambarKamar;
    @ManyToMany(mappedBy = "jenisKamars")
    private List<Fasilitas> fasilitas= new ArrayList<>();
    @OneToMany(mappedBy = "jenisKamar")
    private List<Kamar> kamars=new ArrayList<>();
    @OneToMany(mappedBy = "jenisKamarr")
    private List<DetailReservasi> detailReservasis=new ArrayList<>();
}
