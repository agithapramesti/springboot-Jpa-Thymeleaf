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
@Table(name = "fasilitas_berbayar")
public class FasilitasBerbayar {
    @GeneratedValue(strategy = GenerationType.AUTO) @Id
    @Column(name = "fasilitas_berbayar_id")
    private int fasilitasBerbayarId;
    @Column(name = "nama_fasilitas",nullable = false)
    @Size(max = 255)
    private String namaFasilitas;
    @Column(name = "harga_fasilitas",nullable = false)
    private double hargaFasilitas;
    @OneToMany(mappedBy = "fasilitasBerbayar")
    private List<PermintaanKhusus> permintaanKhususes=new ArrayList<>();
}
