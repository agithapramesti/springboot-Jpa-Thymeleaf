package com.teknikinformatika.tugaspppl.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jenisk")
public class JenisK {
    @Id
    private int jenisKamarId;
    private String namaJenisKamar;
    private double hargaJenisKamar;
    private int kapasitas;
    private String gambarKamar;
    private int stok;

}
