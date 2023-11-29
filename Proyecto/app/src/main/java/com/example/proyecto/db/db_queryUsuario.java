package com.example.proyecto.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class db_queryUsuario extends dbHelper{

    Context context;

    public db_queryUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /*
    private static String nombre;
    private static String apellido;
    private static String email;
    private static String contrasena;
*/


    public long insertUsuario(String nombre, String apellido, String email,String contrasena){

        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("apelllido",apellido);
        valores.put("email ",email);
        valores.put("contrasena",contrasena);
        valores.put("nivel",0);

        long id = db.insert(TABLE_USUARIO,null ,valores);

        return id;
    }

    public Cursor MostrarUsuarios(){

        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        Cursor c = db.query(
                TABLE_USUARIO,  // Nombre de la tabla
                null,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
        );

        //c.close();

        return c;
    }


    @SuppressLint("Range")
    public Cursor buscarUsuariosLogin(String nom, String password){


        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        Cursor c = null;
        int nivel = 2;



        try{
             c = db.rawQuery("SELECT * FROM "+TABLE_USUARIO+" WHERE email = '"+nom+"'AND contrasena = '"+password+"'",null);



        }catch (Exception ex){
            ex.toString();
        }

        return c;
    }

    public Cursor buscarUsuario(String nom){


        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        Cursor c = null;
        int nivel = 2;



        try{
            c = db.rawQuery("SELECT * FROM "+TABLE_USUARIO+" WHERE email = '"+nom+"'",null);



        }catch (Exception ex){
            ex.toString();
        }

        return c;
    }

    public boolean editarUsuario(String nom, String apelli, String email,String contrase){

        boolean logro = false;
        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();//++"

        try{
            db.execSQL("UPDATE "+TABLE_USUARIO+" SET nombre = '"+nom+"', apelllido = '"+apelli+"', email  = '"+email+"', contrasena = '"+contrase+"', nivel = '"+0+"' WHERE email='"+email+"' ");

            logro = true;
        }catch (Exception ex){
            ex.toString();
        }

        return logro;
    }


    public int eliminarUsuario(String nom){

        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        String  parteQuery = "email"+ " LIKE ?";

        String[] datoAELiminar = { nom };
// Issue SQL statement.
        int deletedRows = db.delete(TABLE_USUARIO, parteQuery, datoAELiminar);

        return deletedRows;
    }
}
