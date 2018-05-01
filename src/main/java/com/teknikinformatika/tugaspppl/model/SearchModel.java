package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchModel {
    private Date tanggalCheckIn, tanggalCheckOut;
    private int cabangId, jumlahAnak, jumlahDewasa;
}
