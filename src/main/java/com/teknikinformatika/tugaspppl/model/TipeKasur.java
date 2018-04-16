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
@Table(name = "tipe_kasur")
public class TipeKasur {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_tipe")
    private int idTipe;
    @Column(name = "status_tipe",nullable = false)
    private int statusTipe;
    @Column(name = "nama_tipe",nullable = false)
    @Size(max = 255)
    private String namaTipe;

    @OneToMany(mappedBy = "tipeKasur")
    private List<Kamar> kamars=new ArrayList<>();

}
