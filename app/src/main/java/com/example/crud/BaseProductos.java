package com.example.crud;

public class BaseProductos {

    public static final String TABLA_USUARIO="usuarios";
    public static final String ID_USUARIO="id_usuario ";
    public static final String NOMBRE_CORTO="Nombre_corto ";
    public static final String CONTRASENIA="Contrasenia ";
    public static final String PERMISOS="Permisos ";
    public static final String FOTO="Foto ";

    public static final String CREAR_TABLA_USUARIO = " CREATE TABLE "+TABLA_USUARIO+" ("+ ID_USUARIO+" TEXT PRIMARY KEY, " +
            " "+NOMBRE_CORTO+" TEXT, "+CONTRASENIA+" TEXT, "+PERMISOS+" TEXT, "+FOTO+" TEXT )";



}
