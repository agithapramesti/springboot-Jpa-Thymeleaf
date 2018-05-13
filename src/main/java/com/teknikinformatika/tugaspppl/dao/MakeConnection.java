package com.teknikinformatika.tugaspppl.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MakeConnection {
    protected Connection con;
    public MakeConnection(){}
    public MakeConnection(Connection con){
        this.con=con;
    }
    public void makeConnection(){
        System.out.println("Openning database...");
        try{
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test01",
                    "root",
                    ""
            );
            System.out.println("Success openning db!");
        }
        catch (Exception e){
            System.out.println("Error while openning db");
            System.out.println(e);
        }
    }
    public void disconnect(){
        System.out.println("Open disconnect");
        try{
            this.con.close();
            System.out.println("Success disconnect");
        }
        catch (Exception e){
            System.out.println("Error while disconnect db");
            System.out.println(e);
        }
    }
}