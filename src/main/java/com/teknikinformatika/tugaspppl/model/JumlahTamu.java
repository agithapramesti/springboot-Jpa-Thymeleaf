package com.teknikinformatika.tugaspppl.model;

public class JumlahTamu {
    private String namaJenisKamar;
    private int grup,personal, total;
    public JumlahTamu(){}
    public JumlahTamu(String namaJenisKamar, int grup, int personal, int total) {
        this.namaJenisKamar = namaJenisKamar;
        this.grup = grup;
        this.personal = personal;
        this.total = total;
    }

    public String getNamaJenisKamar() {
        return namaJenisKamar;
    }

    public void setNamaJenisKamar(String namaJenisKamar) {
        this.namaJenisKamar = namaJenisKamar;
    }

    public int getGrup() {
        return grup;
    }

    public void setGrup(int grup) {
        this.grup = grup;
    }

    public int getPersonal() {
        return personal;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
