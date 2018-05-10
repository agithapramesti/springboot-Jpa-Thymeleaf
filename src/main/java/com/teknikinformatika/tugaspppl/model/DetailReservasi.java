package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detail_reservasi")
public class DetailReservasi {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "detail_id")
    private int detailId;
    @Column(name = "tanggal_check_in", nullable = false)
    private Date tanggalCheckIn;
    @Column(name = "tanggal_check_out", nullable = false)
    private Date tanggalCheckOut;
    @Column(name = "subTotal_detail", nullable = false)
    private double subtotalDetail;

    @ManyToOne
    @JoinColumn(name = "jenis_kamar_id",insertable=false, updatable=false)
    private JenisKamar jenisKamarr;
    @ManyToOne
    @JoinColumn(name = "reservasi_id",insertable=false, updatable=false)
    private Reservasi reservasi;
    @ManyToOne
    @JoinColumn(name = "kamar_id",insertable=false, updatable=false)
    private Kamar kamar;
}
