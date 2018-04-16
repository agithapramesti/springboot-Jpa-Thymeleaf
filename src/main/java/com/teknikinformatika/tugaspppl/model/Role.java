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
@Table(name = "role")
public class Role {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "status_role")
    private int statusRole;
    @Column(name = "nama_role")
    @Size(max = 25)
    private String namaRole;
    @OneToMany(mappedBy = "role")
    private List<User> users=new ArrayList<>();

}
