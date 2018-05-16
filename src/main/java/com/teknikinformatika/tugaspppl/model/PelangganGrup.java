package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pelanggan_grup")
public class PelangganGrup {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "nama_institusi", nullable = false)
    @Size(max = 100)
    private String namaInstitusi;
    @Column(name = "nama_ketua", nullable = false)
    @Size(max = 100)
    private String namaKetua;
    @Column(name = "no_telp_ketua", nullable = false)
    @Size(max = 20)
    private String noTelpKetua;
    @Column(name = "email_ketua", nullable = false)
    @Size(max = 100)
    private String emailKetua;
    @Column(name = "alamat_ketua", nullable = false)
    @Size(max = 40)
    private String alamatKetua;
    @Column(name = "no_identitas_ketua", nullable = false)
    @Size(max = 20)
    private String noIdentitasKetua;
    private Date tanggalGrup;
    @ManyToOne
    @JoinColumn(name = "user_id",insertable=false, updatable=false)
    private User user;
}
