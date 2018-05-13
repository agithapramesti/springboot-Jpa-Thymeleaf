package com.teknikinformatika.tugaspppl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class PendapatanBulanan {
    private String tanggal;
    private double personal, grup, total;
    public PendapatanBulanan(){}
    public PendapatanBulanan(String tanggal, double personal, double grup, double total) {
        this.tanggal = tanggal;
        this.personal = personal;
        this.grup = grup;
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getPersonal() {
        return personal;
    }

    public void setPersonal(double personal) {
        this.personal = personal;
    }

    public double getGrup() {
        return grup;
    }

    public void setGrup(double grup) {
        this.grup = grup;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
