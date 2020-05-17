package com.uam.rentadeautos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    Conectar conectar;
    EditText brand,model,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVistas();
        conectar = new Conectar();
    }

    public void inicializarVistas(){
        brand = (EditText) findViewById(R.id.inputBrand);
        model = (EditText) findViewById(R.id.inputModel);
        year = (EditText) findViewById(R.id.inputYear);
    }


    public void submit(View v){
        if(brand.getText().toString().equals("")||model.getText().toString().equals("")||year.getText().toString().equals("")){
            Toast.makeText(this,"Debe llenar todos los campos.",Toast.LENGTH_SHORT).show();
        }else{
            agregarAuto agregarAuto = new agregarAuto();
            agregarAuto.execute("");
        }
    }

    public class agregarAuto extends AsyncTask<String,String,String>
    {
        String brandstr=brand.getText().toString();
        String modelstr=model.getText().toString();
        String yearstr=year.getText().toString();
        int yearint=Integer.valueOf(yearstr);
        String z="";
        boolean isSuccess=false;
        @Override
        protected void onPreExecute() {
            //progressDialog.setMessage("Loading...");
            //progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
                try {
                    Connection con = conectar.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        String query="INSERT INTO autos VALUES ('" + brandstr + "','" + modelstr + "'," + yearint + "," + false + ");";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        z = "El auto ha sido agregado satisfactoriamente";
                        isSuccess=true;
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                    Log.e("ERRO", z);
                }

            return z;
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
            if(isSuccess) {
                //startActivity(new Intent(MainActivity.this,Main2Activity.class));
                brand.setText("");
                model.setText("");
                year.setText("");
            }
            //progressDialog.hide();
        }
    }




}



