package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permintaan_khusus")
public class PermintaanKhusus {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "permintaan_id")
    private int permintaanId;

    @Column(name = "jumlah",nullable = false)
    private int jumlah;
    @Column(name = "reservasi_id",nullable = false)
    private String reservasId;
    @Column(name = "subtotal_permintaan",nullable = false)
    private double subtotalPermintaan;
    @Column(name = "item_price",nullable = false)
    private double itemPrice;
    @ManyToOne
    @JoinColumn(name = "reservasi_id",insertable=false, updatable=false)
    private Reservasi reservasis;
    @ManyToOne
    @JoinColumn(name = "fasilitas_berbayar_id",insertable=false, updatable=false)
    private FasilitasBerbayar fasilitasBerbayar;
}
