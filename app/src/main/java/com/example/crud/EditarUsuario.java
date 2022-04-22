package com.example.crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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

public class EditarUsuario extends AppCompatActivity {

    EditText txtId, txtNombre, txtPas, txtPermisos, txtBuscar;
    ImageView imgFoto;
    Button btnActualizar, btnBuscar, btnEliminar;
    ImageButton btnCargarFoto;
    String imagenBase64="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        BuscarUsuario();
        SeleccionarFoto();
        ActualizarUsuario();
        EliminarUsuario();
    }


    public void BuscarUsuario(){
        btnBuscar = findViewById(R.id.btnBuscar);
        txtBuscar = findViewById(R.id.editTextBuscarID);
        txtId = findViewById(R.id.editTextIdUsuarioEdit);
        txtNombre = findViewById(R.id.editTextNombreCortoEDit);
        txtPas = findViewById(R.id.editTextContraseniaEdit);
        txtPermisos = findViewById(R.id.editTextPermisosEdit);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuarioBuscar = new Usuario();
                usuarioBuscar.buscarUsuario(EditarUsuario.this, usuarioBuscar, txtBuscar.getText().toString());
                txtId.setText(usuarioBuscar.getId_usuario());
                txtNombre.setText(usuarioBuscar.getNombre_corto());
                txtPas.setText(usuarioBuscar.getContrasenia());
                txtPermisos.setText(usuarioBuscar.getPermisos());
                //Decodificamos la foto subida a la base de datos
                byte[] bytes = Base64.decode(usuarioBuscar.getFoto(), Base64.DEFAULT);
                //Mapeado de la foto
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgFoto.setImageBitmap(bitmap);
            }
        });
    }


    public void ActualizarUsuario(){
        btnActualizar = findViewById(R.id.btnActualizar);
        txtId = findViewById(R.id.editTextIdUsuarioEdit);
        txtNombre = findViewById(R.id.editTextNombreCortoEDit);
        txtPas = findViewById(R.id.editTextContraseniaEdit);
        txtPermisos = findViewById(R.id.editTextPermisosEdit);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuarioActual = new Usuario();
                usuarioActual.setNombre_corto(txtNombre.getText().toString());
                usuarioActual.setContrasenia(txtPas.getText().toString());
                usuarioActual.setPermisos(txtPermisos.getText().toString());
                usuarioActual.setFoto(imagenBase64);
                usuarioActual.ActualizarUsuario(EditarUsuario.this, txtId.getText().toString());
                Intent intent = new Intent(EditarUsuario.this, listado_usuario.class);
                startActivity(intent);
            }
        });
    }

    //Autor John Aaron Viracocha Jara
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
            imagenBase64 = Base64Imagen(imageuri);
        }
    }


    public void EliminarUsuario() {
        btnEliminar = findViewById(R.id.btnEliminar);
        txtId = findViewById(R.id.editTextIdUsuarioEdit);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuarioEliminar = new Usuario();
                usuarioEliminar.EliminarUsuario(EditarUsuario.this, txtId.getText().toString());
                Toast.makeText(EditarUsuario.this, "Elimino Correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarUsuario.this, listado_usuario.class);
                startActivity(intent);
            }
        });
    }

}