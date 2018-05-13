package com.teknikinformatika.tugaspppl.model;

public class CustomerBaru {
    private String bulan;
    private int jumlah;

    public CustomerBaru(String bulan, int jumlah) {
        this.bulan = bulan;
        this.jumlah = jumlah;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
