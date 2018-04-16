package com.teknikinformatika.tugaspppl.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabang")
public class Cabang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cabang_id")
    private int cabangId;

    @Column(name = "status_cabang", nullable = false)
    private int statusCabang;

    @Column(name = "nama_cabang", nullable = false)
    @Size(max = 255)
    private String namaCabang;

    @ManyToMany(mappedBy = "cabangs")
    private List<Kamar> kamars= new ArrayList<>();

    @OneToMany(mappedBy = "cabang")
    private List<User> users=new ArrayList<>();
    @OneToMany(mappedBy = "cabangs")
    private List<Season> seasons=new ArrayList<>();
    @OneToMany(mappedBy = "cabang_res")
    private List<Reservasi> reservasis=new ArrayList<>();
}
