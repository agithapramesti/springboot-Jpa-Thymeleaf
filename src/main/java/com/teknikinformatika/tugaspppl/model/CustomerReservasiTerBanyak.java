package com.teknikinformatika.tugaspppl.model;

public class CustomerReservasiTerBanyak {
    private String nama;
    private int jumlahReservasi;
    private double totalTrans;
    public CustomerReservasiTerBanyak(){}
    public CustomerReservasiTerBanyak(String nama, int jumlahReservasi, double totalTrans) {
        this.nama = nama;
        this.jumlahReservasi = jumlahReservasi;
        this.totalTrans = totalTrans;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlahReservasi() {
        return jumlahReservasi;
    }

    public void setJumlahReservasi(int jumlahReservasi) {
        this.jumlahReservasi = jumlahReservasi;
    }

    public double getTotalTrans() {
        return totalTrans;
    }

    public void setTotalTrans(double totalTrans) {
        this.totalTrans = totalTrans;
    }
}
