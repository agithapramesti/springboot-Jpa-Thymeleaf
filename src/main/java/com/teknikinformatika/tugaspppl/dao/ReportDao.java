package com.teknikinformatika.tugaspppl.dao;

import com.teknikinformatika.tugaspppl.model.CustomerBaru;
import com.teknikinformatika.tugaspppl.model.JumlahTamu;
import com.teknikinformatika.tugaspppl.model.PendapatanBulanan;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDao extends MakeConnection
{
    public List<PendapatanBulanan> getAllPendapatanBulanan(){
        String psql="SELECT FirstSet.tanggal, IFNULL(SecondSet.Personal,0) AS 'Personal', IFNULL(ThirdSet.Grup,0) AS 'Grup', IFNULL(SecondSet.Personal,0)+IFNULL(ThirdSet.Grup,0) AS 'Total'\n" +
                "FROM \n" +
                "( \n" +
                "SELECT date_format(r.tanggal_reservasi,'%M') AS 'tanggal' FROM reservasi r GROUP BY date_format(r.tanggal_reservasi,'%M') \n" +
                ") AS FirstSet \n" +
                "LEFT OUTER JOIN \n" +
                "( \n" +
                "SELECT date_format(r.tanggal_reservasi,'%M') as 'tanggal', sum(r.total_transaksi) as \"Personal\" FROM reservasi r WHERE r.jenis_reservasi = 'P' GROUP by date_format(r.tanggal_reservasi,'%M') \n" +
                ") as SecondSet \n" +
                "on FirstSet.tanggal = SecondSet.tanggal \n" +
                "left outer JOIN \n" +
                "( \n" +
                "SELECT date_format(r.tanggal_reservasi,'%M') as 'tanggal', sum(r.total_transaksi) as \"Grup\" FROM reservasi r WHERE r.jenis_reservasi = 'G' GROUP by date_format(r.tanggal_reservasi,'%M') \n" +
                ")as ThirdSet \n" +
                "on FirstSet.tanggal = ThirdSet.tanggal";
        List<PendapatanBulanan> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){
                    PendapatanBulanan pendapatanBulanan = new PendapatanBulanan(
                            rs.getString("tanggal"),
                            rs.getDouble("Personal"),
                            rs.getDouble("Grup"),
                            rs.getDouble("Total")
                    );
                    temp.add(pendapatanBulanan);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return temp;
    }
    public List<CustomerBaru> getAllCustomerBaruYearly(){
        String psql="SELECT MONTHNAME(U.tanggal_user) AS 'Bulan', COUNT(U.user_id) AS 'Jumlah' FROM user U WHERE U.role_id = 7 AND MONTHNAME(U.tanggal_user) is not null and YEAR(U.tanggal_user)=YEAR(CURRENT_DATE) GROUP BY MONTHNAME(U.tanggal_user) ASC";
        List<CustomerBaru> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){
                    CustomerBaru customerBaru = new CustomerBaru(rs.getString("Bulan"), rs.getInt("Jumlah"));
                    temp.add(customerBaru);
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return temp;
    }

    public List<JumlahTamu> getJumlahTamuJenisKamar(){
        String psql="SELECT FirstSet.namaJenisKamar, IFNULL(SecondSet.jumlahOrang,0) AS 'Personal', IFNULL(ThirdSet.jumlahOrang,0) AS 'Grup', IFNULL(SecondSet.jumlahOrang,0)+IFNULL(ThirdSet.jumlahOrang,0) AS 'Total' \n" +
                "FROM \n" +
                "( \n" +
                "SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', jenis_kamar.nama_jenis_kamar as 'namaJenisKamar' FROM jenis_kamar \n" +
                ") AS FirstSet \n" +
                "LEFT OUTER JOIN \n" +
                "( SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', sum(reservasi.jumlah_orang) as 'jumlahOrang' FROM detail_reservasi JOIN reservasi ON(detail_reservasi.reservasi_id=reservasi.reservasi_id) JOIN jenis_kamar ON(detail_reservasi.jenis_kamar_id=jenis_kamar.jenis_kamar_id) WHERE detail_reservasi.reservasi_id IN (SELECT reservasi.reservasi_id FROM reservasi WHERE MONTH(reservasi.tanggal_reservasi)=MONTH(CURRENT_DATE) AND reservasi.jenis_reservasi='P') GROUP BY detail_reservasi.jenis_kamar_id \n" +
                ") as SecondSet \n" +
                "on FirstSet.jenisKamarId = SecondSet.jenisKamarId \n" +
                "left outer JOIN \n" +
                "( \n" +
                "SELECT jenis_kamar.jenis_kamar_id as 'jenisKamarId', sum(reservasi.jumlah_orang) as 'jumlahOrang' FROM detail_reservasi JOIN reservasi ON(detail_reservasi.reservasi_id=reservasi.reservasi_id) JOIN jenis_kamar ON(detail_reservasi.jenis_kamar_id=jenis_kamar.jenis_kamar_id) WHERE detail_reservasi.reservasi_id IN (SELECT reservasi.reservasi_id FROM reservasi WHERE MONTH(reservasi.tanggal_reservasi)=MONTH(CURRENT_DATE) AND reservasi.jenis_reservasi='G') GROUP BY detail_reservasi.jenis_kamar_id \n" +
                ") as ThirdSet \n" +
                "on FirstSet.jenisKamarId = ThirdSet.jenisKamarId ";
        List<JumlahTamu> temp = new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    JumlahTamu jumlahTamu = new JumlahTamu(
                            rs.getString("namaJenisKamar"),
                            rs.getInt("personal"),
                            rs.getInt("grup"),
                            rs.getInt("total")
                    );
                    temp.add(jumlahTamu);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return temp;
    }
}
