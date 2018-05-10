package com.teknikinformatika.tugaspppl.model;

public class SearchTambahDurasi {
    private int reservasiId;
    private int durasi;
    public SearchTambahDurasi(){}

    public SearchTambahDurasi(int reservasiId, int durasi) {
        this.reservasiId = reservasiId;
        this.durasi = durasi;
    }

    public int getReservasiId() {
        return reservasiId;
    }

    public void setReservasiId(int reservasiId) {
        this.reservasiId = reservasiId;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }
}
