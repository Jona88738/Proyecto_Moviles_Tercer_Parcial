package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.Clases.Usuario;
import com.example.proyecto.Root.UsrRoot;
import com.example.proyecto.db.dbHelper;
import com.example.proyecto.db.db_queryUsuario;


public class MainActivity extends AppCompatActivity {
    private EditText email, contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.etEmaiLogin);
        contrasena = findViewById(R.id.etContrasenaLogin);

        dbHelper dbHel = new dbHelper(this);

        SQLiteDatabase db = dbHel.getWritableDatabase();


    }

    @SuppressLint("Range")
    public void btnIngresar(View view) {




        int bandera = 0;
        String nombre = "";
        String correo = email.getText().toString();
        String password = contrasena.getText().toString();
        int nivel = 3;
        if(correo.equals("") && password.equals("")){
           Toast mensaje = Toast.makeText(this, "Datos no ingresados", Toast.LENGTH_LONG);
            mensaje.show();
        }else{

            db_queryUsuario db = new db_queryUsuario(this);
            Cursor c = db.buscarUsuariosLogin(correo,password);

            while(c.moveToNext()){
                nombre = c.getString(1);
                nivel = c.getInt(5);
               // na =  c.getString(c.getColumnIndex("nombre"));
                // Acciones...nombre
            }
            if(c != null && nivel != 3){

                Log.i("variable nivel","d: "+nivel);
                if(nivel > 0){
                    Intent intent = new Intent(this, UsrRoot.class);
                    startActivity(intent);
                    bandera++;
                    Toast.makeText(this, "Bienvenido Admi  "+nombre, Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(this, MenuLateral.class);
                    startActivity(intent);
                    bandera++;
                    Toast.makeText(this, "Bienvenido "+nombre, Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast mensaje = Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_LONG);
                mensaje.show();
            }



        }

    }

    public void registrarse(View view) {
        Intent intent = new Intent(MainActivity.this, Register_activity.class);

        startActivity(intent);
    }
}