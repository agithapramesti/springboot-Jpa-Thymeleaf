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
@Table(name = "fasilitas")
public class Fasilitas {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "fasilitas_id")
    private int fasilitasId;
    @Column(name = "status_fasilitas",nullable = false)
    private int statusFasilitas;
    @Column(name = "nama_fasilitas",nullable = false)
    @Size(max = 255)
    private String namaFasilitas;

    @ManyToMany
    @JoinTable(
            name = "fasilitas_jenis_kamar",
            joinColumns = { @JoinColumn(name = "fasilitas_id") },
            inverseJoinColumns = { @JoinColumn(name = "jenis_kamar_id") }
    )
    private List<JenisKamar> jenisKamars= new ArrayList<>();

}
