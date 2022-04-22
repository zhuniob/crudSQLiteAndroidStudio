package com.example.crud;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolderDatos> {

    ArrayList<Usuario>lista_users;

    public AdaptadorUsuario(ArrayList<Usuario> lista_users) {
        this.lista_users = lista_users;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
            holder.txtId.setText(lista_users.get(position).getId_usuario());
            holder.txtNombre.setText(lista_users.get(position).getNombre_corto());
            holder.txtPass.setText(lista_users.get(position).getContrasenia());
            holder.txtPermisos.setText(lista_users.get(position).getPermisos());
            //Sirve para decodificar fotos
            byte[] bytes = Base64.decode(lista_users.get(position).getFoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.imgFoto.setImageBitmap(bitmap);
            //Hasta aqui para decodificar foto
    }

    @Override
    public int getItemCount() {
        return lista_users.size();
    }




    //Crear Clase con el mismo nombre se extendie a Recycler View
    //Vamos a llamar las variables de mi item lista usuario
    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        TextView txtId, txtNombre, txtPass, txtPermisos;
        ImageView imgFoto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtidUsuarioVer);
            txtNombre = itemView.findViewById(R.id.txtNombreVer);
            txtPass = itemView.findViewById(R.id.txtContraseniaVer);
            txtPermisos = itemView.findViewById(R.id.txtPermisosVer);
            imgFoto = itemView.findViewById(R.id.imgFotoVer);

        }
    }

}
