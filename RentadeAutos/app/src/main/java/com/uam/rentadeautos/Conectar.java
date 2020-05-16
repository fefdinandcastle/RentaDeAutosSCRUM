package com.uam.rentadeautos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conectar {
    private Connection conexion = null;
    private String servidor="192.168.0.10:3306";
    private String database="renta_autos";
    private String usuario="Luis";
    private String password="Lossims4";
    private String url="";

    public Conectar(Context context){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://"+servidor+"/"+database;
            conexion = DriverManager.getConnection(url, usuario, password);
            boolean bool = conexion==null;
            //Toast.makeText(context,"Al inicio es null: "+bool,Toast.LENGTH_SHORT).show();
        }catch (ClassNotFoundException | SQLException ex){
            Log.d("xd",ex.getMessage());
        }
    }

    public Connection getConexion(){
        return conexion;
    }

    public Connection cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        conexion=null;
        return conexion;
    }
}

