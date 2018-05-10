package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JenisKamarTemp {
  @Id
  private int jenisKamarId;
  private int stok;
  private String namaJenisKamar;
  private double hargaKamar;
}
