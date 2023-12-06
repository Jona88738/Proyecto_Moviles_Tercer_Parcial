package com.example.proyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private  static final int db_Version = 1;
    private static final String db_name = "ProyectoComida.db";

    public static final String TABLE_USUARIO = "usuario";
    public static final String TABLE_GUARDABOSQUE= "GuardaBosque";

    /*
    private static String nombre;
    private static String apellido;
    private static String email;
    private static String contrasena;
*/


    private static final String SQL_TABLE_USUARIO =
            "CREATE TABLE " +TABLE_USUARIO + " (" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "nombre TEXT NOT NULL,"+
                    "apelllido TEXT NOT NULL,"+
                    "email TEXT NOT NULL,"+
                    "contrasena TEXT NOT NULL,"+
                    "nivel INTEGER NOT NULL)";

    private static final String SQL_TABLE_GUARDABOSQUE =
            "CREATE TABLE " +TABLE_GUARDABOSQUE + " (" +
                    "id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "nombre TEXT NOT NULL,"+
                    "edad INT NOT NULL,"+
                    "sexo TEXT NOT NULL,"+
                    "nacionalidad TEXT NOT NULL,"+
                    "PESO INT NOT NULL"+")";

    private static final String SQL_DELETE_USUARIO =
            "DROP TABLE  " + TABLE_USUARIO;

    private static final String SQL_DELETE_GuardaBosque =
            "DROP TABLE  " + TABLE_GUARDABOSQUE;

    public dbHelper(@Nullable Context context) {
        super(context, db_name, null, db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        ContentValues valores = new ContentValues();
        valores.put("nombre","Jona");
        valores.put("apelllido","perez");
        valores.put("email ","jona");
        valores.put("contrasena","123");
        valores.put("nivel",1);
        db.execSQL(SQL_TABLE_USUARIO);

        db.insert(TABLE_USUARIO,null ,valores);
        //db.execSQL();
        db.execSQL(SQL_TABLE_GUARDABOSQUE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_USUARIO);
        db.execSQL(SQL_DELETE_GuardaBosque);
        onCreate(db);

    }
}
