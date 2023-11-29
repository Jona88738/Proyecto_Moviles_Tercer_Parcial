package com.example.proyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class db_query extends dbHelper{

    Context context;

    public db_query(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertParque(String nombre, int  ctnArboles, String ubicacion,int fechaIncio,int instalacionesAyuda){

        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("cantArboles",ctnArboles);
        valores.put("ubicacion",ubicacion);
        valores.put("fechaInicio",fechaIncio);
        valores.put("instalacionesAyuda",instalacionesAyuda);

       long id = db.insert(TABLE_USUARIO,null ,valores);

       return id;
    }

    public Cursor MostrarParque(){

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

    public Cursor buscarParque(String nom){


        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_USUARIO+" WHERE nombre = '"+nom+"' LIMIT 1",null);

        return c;
    }

    public boolean editarParque(String nom, int  ctnArboles, String ubicacion,int fechaIncio,int instalacionesAyuda){

        boolean logro = false;
        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();//++"

        try{
            db.execSQL("UPDATE "+TABLE_USUARIO+" SET nombre = '"+nom+"', cantArboles = '"+ctnArboles+"', ubicacion = '"+ubicacion+"', fechaInicio = '"+fechaIncio+"', instalacionesAyuda = '"+instalacionesAyuda+"' WHERE nombre='"+nom+"' ");

            logro = true;
        }catch (Exception ex){
            ex.toString();
        }

        return logro;
    }


    public int eliminarDatoParque(String nom){

        dbHelper dbHelp = new dbHelper(context);
        SQLiteDatabase db = dbHelp.getWritableDatabase();

        String  parteQuery = "nombre"+ " LIKE ?";

        String[] datoAELiminar = { nom };
// Issue SQL statement.
        int deletedRows = db.delete(TABLE_USUARIO, parteQuery, datoAELiminar);

        return deletedRows;
    }


}
