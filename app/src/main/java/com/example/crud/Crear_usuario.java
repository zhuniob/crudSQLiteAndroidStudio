package com.example.crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Crear_usuario extends AppCompatActivity {

    EditText txtid, txtNombre, txtPass, txtPermisos;
    ImageView imgFoto;
    Button btnGuardar;
    ImageButton btnCargarFoto;
    String Base64Imagen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        SeleccionarFoto();
        GuardarUsuario();
    }

    public void GuardarUsuario(){
        btnGuardar = findViewById(R.id.btnActualizar);
        txtid = findViewById(R.id.editTextIdUsuarioEdit);
        txtNombre = findViewById(R.id.editTextNombreCortoEDit);
        txtPass = findViewById(R.id.editTextContraseniaEdit);
        txtPermisos = findViewById(R.id.editTextPermisosEdit);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuarioActual = new Usuario();
                usuarioActual.setId_usuario(txtid.getText().toString());
                usuarioActual.setNombre_corto(txtNombre.getText().toString());
                usuarioActual.setContrasenia(txtPass.getText().toString());
                usuarioActual.setPermisos(txtPermisos.getText().toString());
                usuarioActual.setFoto(Base64Imagen);
                usuarioActual.CrearUsuario(Crear_usuario.this);
                Toast.makeText(Crear_usuario.this, "Se creo el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String Base64Imagen(Uri image){
        String Base64s="";
        try {
            //Iniciamos el mapeo de la imagen
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
            //Iniciamos el array de bytes
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //Comprimimos bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            //Iniciamos el array de bytes
            byte[] bytes = stream.toByteArray();
            //Obtenemos el base64
            Base64s = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64s;
    }

    public void SeleccionarFoto(){
        btnCargarFoto = findViewById(R.id.BtnCArgarFotoEdit);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Seleccion Foto"), 10);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageuri = data.getData();
            imgFoto.setImageURI(imageuri);
            Base64Imagen = Base64Imagen(imageuri);
        }
    }
}