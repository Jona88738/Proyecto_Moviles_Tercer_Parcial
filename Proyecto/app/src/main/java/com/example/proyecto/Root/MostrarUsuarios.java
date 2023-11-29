package com.example.proyecto.Root;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import com.example.proyecto.R;
import com.example.proyecto.db.db_query;
import com.example.proyecto.db.db_queryUsuario;

public class MostrarUsuarios extends AppCompatActivity {

    EditText mostrar;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);
        mostrar = findViewById(R.id.etListadoUsuarios);


        db_queryUsuario dbQuery = new db_queryUsuario(this);
        Cursor c = dbQuery.MostrarUsuarios();
        String name = "";
        String apelli = "";
        String correo = "";
        String contra = "";
        int nivel = 0;


        String cuadroTexto = "";
        while(c.moveToNext()){

            name = c.getString(c.getColumnIndex("nombre"));
            apelli = c.getString(c.getColumnIndex("apelllido"));
            correo = c.getString(c.getColumnIndex("email"));
            contra = c.getString(c.getColumnIndex("contrasena"));
            nivel =   c.getInt(c.getColumnIndex("nivel"));

            cuadroTexto += "\nNombre: "+name+"\nApellido: "+apelli+"\nCorreo: "+correo+"\nContraseña: "+contra+"\nNivel: "+
                    nivel+"\n";
            // Acciones...
        }
        mostrar.setText(cuadroTexto);

    }
}