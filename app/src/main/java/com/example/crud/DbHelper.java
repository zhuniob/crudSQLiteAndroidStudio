package com.example.crud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOMBRE = "baseUsuario.db";
    private static final int DATABASE_VERSION =1;


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BaseProductos.CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BaseProductos.TABLA_USUARIO);
        onCreate(sqLiteDatabase);
    }

    //Ejecuta Insert, Update, Delete
    protected void noQuery(String nsql){
        this.getWritableDatabase().execSQL(nsql);
    }

    //Ejecuta solo select o consultas
    protected Cursor query(String sql){
        return this.getReadableDatabase().rawQuery(sql, null);
    }

}
