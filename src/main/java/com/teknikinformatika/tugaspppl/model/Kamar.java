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
@Table(name = "kamar")
public class Kamar {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "kamar_id")
    private int kamarId;
    @Column(name = "lantai",nullable = false)
    private int lantai;
    @Column(name = "status_kamar",nullable = false)
    private int statusKamar;
    @Column(name = "nomor_kamar",nullable = false)
    private int nomorKamar;

    @Column(name = "status_rokok",nullable = false)
    @Size(max = 255)
    private String statusRokok;
    @Column(name = "kode_kamar",nullable = false)
    @Size(max = 255)
    private String kodeKamar;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "kamar_cabang",
            joinColumns = { @JoinColumn(name = "kamar_id") },
            inverseJoinColumns = { @JoinColumn(name = "cabang_id") }
    )
    private List<Cabang> cabangs;

    @ManyToOne
    @JoinColumn(name = "id_tipe",insertable=true, updatable=true)
    private TipeKasur tipeKasur;
    @ManyToOne
    @JoinColumn(name = "jenis_kamar_id",insertable=true, updatable=true)
    private JenisKamar jenisKamar;
    @OneToMany(mappedBy = "kamar")
    private List<DetailReservasi> detailReservasis=new ArrayList<>();

    public boolean hasCabang(Cabang cabang) {
        for (Cabang kamarCabang: cabangs) {
            if (kamarCabang.getCabangId() == cabang.getCabangId()) {
                return true;
            }
        }
        return false;
    }
}
