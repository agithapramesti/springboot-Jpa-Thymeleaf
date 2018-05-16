package com.teknikinformatika.tugaspppl.model;

public class PendapatanCabangPertahun {
    private int bulan;
    private double yogyakarta, bandung, total;
    public PendapatanCabangPertahun(){}

    public PendapatanCabangPertahun(int bulan, double yogyakarta, double bandung, double total) {
        this.bulan = bulan;
        this.yogyakarta = yogyakarta;
        this.bandung = bandung;
        this.total = total;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public double getYogyakarta() {
        return yogyakarta;
    }

    public void setYogyakarta(double yogyakarta) {
        this.yogyakarta = yogyakarta;
    }

    public double getBandung() {
        return bandung;
    }

    public void setBandung(double bandung) {
        this.bandung = bandung;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
