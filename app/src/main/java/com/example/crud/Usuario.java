package com.example.crud;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Usuario {
    private String id_usuario;
    private String nombre_corto;
    private String contrasenia;
    private String permisos;
    private String foto;
    ArrayList<Usuario> list;

    public Usuario() {
    }

    public Usuario(String id_usuario, String nombre_corto, String contrasenia, String permisos, String foto) {
        this.id_usuario = id_usuario;
        this.nombre_corto = nombre_corto;
        this.contrasenia = contrasenia;
        this.permisos = permisos;
        this.foto = foto;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    //Insert
    public void CrearUsuario(Context context){
        DbHelper db = new DbHelper(context);
        String nosql = "INSERT INTO "+BaseProductos.TABLA_USUARIO+" ("+BaseProductos.ID_USUARIO+", " +
                ""+BaseProductos.NOMBRE_CORTO+", " +
                ""+BaseProductos.CONTRASENIA+", " +
                ""+BaseProductos.PERMISOS+", " +
                ""+BaseProductos.FOTO+") " +
                " VALUES ('"+getId_usuario()+"','"+getNombre_corto()+"','"+getContrasenia()+"', '"+getPermisos()+"','"+getFoto()+"')";
        db.noQuery(nosql);
        db.close();
    }

    //Mostrar Datos Usuario dentro de la base
    public ArrayList<Usuario> lista_Usuarios(Context context){
        DbHelper db = new DbHelper(context);
        String sql = "SELECT * FROM "+BaseProductos.TABLA_USUARIO+";";
        list = new ArrayList<Usuario>();
        Cursor cursor = db.query(sql);

        while (cursor.moveToNext()){
            Usuario usuarioActual = new Usuario();
            usuarioActual.setId_usuario("Id Usuario: "+cursor.getString(0));
            usuarioActual.setNombre_corto("Nombre Corto: "+cursor.getString(1));
            usuarioActual.setContrasenia("Contrasenia: "+cursor.getString(2));
            usuarioActual.setPermisos("Permisos: "+cursor.getString(3));
            usuarioActual.setFoto(cursor.getString(4));
            list.add(usuarioActual);
            db.close();
        }
        return list;
    }

    //Buscar
    public void buscarUsuario(Context context, Usuario usuario, String id){
        DbHelper dbHelper = new DbHelper(context);
        String sql = "SELECT * FROM "+BaseProductos.TABLA_USUARIO+" WHERE id_usuario='"+id+"';";
        Cursor cursor = dbHelper.query(sql);
        while (cursor.moveToNext()){
            usuario.setId_usuario(cursor.getString(0));
            usuario.setNombre_corto(cursor.getString(1));
            usuario.setContrasenia(cursor.getString(2));
            usuario.setPermisos(cursor.getString(3));
            usuario.setFoto(cursor.getString(4));
            dbHelper.close();
        }
    }

    //Actualizar
    public void ActualizarUsuario(Context context, String id){
        DbHelper  dbHelper = new DbHelper(context);
        String nosql = "UPDATE "+BaseProductos.TABLA_USUARIO+" SET " +
                " Nombre_corto='"+getNombre_corto()+"', " +
                " Contrasenia='"+getContrasenia()+"', " +
                " Permisos='"+getPermisos()+"'," +
                " Foto='"+getFoto()+"' " +
                " WHERE id_usuario='"+id+"';";
        dbHelper.noQuery(nosql);
        dbHelper.close();
    }

    //Eliminar
    public void EliminarUsuario(Context context, String id){
        DbHelper dbHelper = new DbHelper(context);
        String nosql = "DELETE FROM "+BaseProductos.TABLA_USUARIO+" WHERE id_usuario='"+id+"';";
        dbHelper.noQuery(nosql);
        dbHelper.close();
    }


}
