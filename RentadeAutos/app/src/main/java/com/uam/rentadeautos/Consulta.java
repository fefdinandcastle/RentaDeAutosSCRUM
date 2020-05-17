package com.uam.rentadeautos;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Consulta {

    public Consulta(){

    }
    public void altaProducto(Context context) {
        try {

            Conectar conn = new Conectar(context);
            Connection conexion = conn.getConexion();
            boolean bool = conexion==null;
            Toast.makeText(context,"Es null: "+bool,Toast.LENGTH_SHORT).show();
            PreparedStatement ps;
            String marca = "Nissan";
            String modelo = "350Z";
            //String insert= "INSERT INTO autos VALUES ('" + marca + "','" + modelo + "'," + 2004 + "," + true + ");";
            //Log.d("logxd",insert);
            ps=conexion.prepareStatement("INSERT INTO autos VALUES ('" + marca + "','" + modelo + "'," + 2004 + "," + true + ");");

            ps.executeUpdate();

            conn.cerrarConexion();

        } catch (SQLException e) {
            System.out.println("error: no se pudo conectar a base de datos");
        } catch (Exception e) {
            System.out.println("error al ejecutar el comando de BD : "+e.getMessage());
        }

    }

}
