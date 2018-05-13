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
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "user_id")//
    private int userId;
    @Column(name = "status_user",nullable = false)
    private int statusUser;
    @Column(name = "username",unique = true) //
    private String username;
    @Column(name = "kata_sandi") //
    private String kataSandi;
    @Column(name = "nama") //
    private String nama;

    private String alamat;
    private String noTelp;
    @Column(name = "no_identitas")
    private String noIdentitas;
    @Column(name = "email")
    private String email;
    @Column(name = "nama_pemegang_kartu")
    private String namaPemegangKartu;
    @Column(name = "no_kartu")
    private String noKartu;
    private Date tanggalUser;
    @ManyToOne
    @JoinColumn(name = "cabang_id",insertable=true, updatable=true)
    private Cabang cabang;

    @ManyToOne
    @JoinColumn(name = "role_id",insertable=true, updatable=true) //
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<PelangganGrup> pelangganGrups=new ArrayList<>();
    @OneToMany(mappedBy = "users")
    private List<Reservasi> reservasis=new ArrayList<>();
}
