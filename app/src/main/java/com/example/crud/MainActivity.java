package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCrearBase, btnCrearUsuario, btnMostrarLista, btnBuscarEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearBase();
        CrearUsuario();
        MostrarLista();
        BuscarVer();
    }


    public void CrearBase(){
        btnCrearBase = findViewById(R.id.btnCrearBase);
        btnCrearBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null){
                    Toast.makeText(MainActivity.this, "Base Usuario Creada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Base Usuario Creada", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void CrearUsuario(){
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Crear_usuario.class);
                startActivity(intent);
            }
        });
    }

    public  void MostrarLista(){
        btnMostrarLista = findViewById(R.id.btnMostrarUsers);
        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, listado_usuario.class);
                startActivity(intent);
            }
        });
    }


    public void BuscarVer(){
        btnBuscarEditar = findViewById(R.id.btnBuscarVer);
        btnBuscarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditarUsuario.class);
                startActivity(intent);
            }
        });
    }


}