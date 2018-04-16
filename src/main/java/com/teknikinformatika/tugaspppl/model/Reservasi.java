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
@Table(name = "reservasi")
public class Reservasi {
    @Id
    @Column(name = "reservasi_id")
    private String reservasId;
//    @Column(name = "cabang_id")
//    private int cabangId;
//    @Column(name = "user_id")
//    private int userId;
    @Column(name = "tanggal_reservasi",insertable=true, updatable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalReservasi;
    @Column(name = "jenis_reservasi", nullable = false)
    private char jenisReservasi;
    @Column(name = "total_transaksi", nullable = false)
    private double totalTransaksi;
    @Column(name = "total_deposit", nullable = false)
    private double totalDeposit;
    @Column(name = "tax", nullable = false)
    private double tax;
    @Column(name = "dp", nullable = false)
    private double dp;
    @Column(name = "sisa_deposit", nullable = false)
    private double sisaDeposit;
    @Column(name = "sisa_pembayaran", nullable = false)
    private double sisaPembayaran;
    @ManyToOne
    @JoinColumn(name = "cabang_id",insertable=false, updatable=false)
    private Cabang cabang_res;
    @OneToMany(mappedBy = "reservasi")
    private List<DetailReservasi> detailReservasis=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id",insertable=false, updatable=false)
    private User users;
    @OneToMany(mappedBy = "reservasis")
    private List<PermintaanKhusus> permintaanKhususes=new ArrayList<>();
}
