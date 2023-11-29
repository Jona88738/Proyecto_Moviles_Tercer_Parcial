package com.example.proyecto.Root;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.db.db_query;
import com.example.proyecto.db.db_queryUsuario;

public class usuarios extends AppCompatActivity {


    EditText nombreBuscar,nombre,apellido,correo,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        nombreBuscar = findViewById(R.id.etNombreBusU);
        nombre = findViewById(R.id.etNombreEncontra);
        apellido = findViewById(R.id.etLastBusEncont);
        correo = findViewById(R.id.etEmailBusEncontr);
        pass = findViewById(R.id.etpassBusEncontra);


    }


    @SuppressLint("Range")
    public void buscar(View view){

        String nom = nombreBuscar.getText().toString();

        if(nombreBuscar.equals("")){
            Toast.makeText(this,"Ingrese un correo",Toast.LENGTH_LONG).show();
        }else {

            db_queryUsuario dbQuery = new db_queryUsuario(this);
            Cursor c = dbQuery.buscarUsuario(nom);
        /*
        valores.put("nombre","Jona");
        valores.put("apelllido","perez");
        valores.put("email ","jona");
        valores.put("contrasena","123");
        valores.put("nivel",1);
         */

            if (c.moveToNext()) {

                String name = "";
                String apelli = "";
                String email = "";
                String contrase = "";
                String nivel = "";
                int id = 0;

                do {
                    name = c.getString(c.getColumnIndex("nombre"));
                    apelli = c.getString(c.getColumnIndex("apelllido"));
                    email = c.getString(c.getColumnIndex("email"));
                    contrase = c.getString(c.getColumnIndex("contrasena"));
                    //nivel =   c.getString(c.getColumnIndex("nivel"));
                    // Acciones...
                }while(c.moveToNext());

                nombre.setText(name);
                apellido.setText(apelli);
                correo.setText(email);
                pass.setText(contrase);



            }else{

                Toast.makeText(this,"Usuario no encontrado",Toast.LENGTH_LONG).show();

            }


        }


    }

    public void Editar(View view ){

        String nomBuscar = nombreBuscar.getText().toString();

        String nom = nombre.getText().toString();
        String last = apellido.getText().toString();
        String email= correo.getText().toString();
        String contra = pass.getText().toString();




        Boolean resultado = false;
        db_queryUsuario dbQuery = new db_queryUsuario(this);
        resultado = dbQuery.editarUsuario(nom,last,email,contra);

        if(resultado == true){
            Toast.makeText(this, "Se edito el contacto", Toast.LENGTH_SHORT).show();
        }else if(resultado == false){
            Toast.makeText(this, "No Se edito el contacto", Toast.LENGTH_SHORT).show();
        }



    }

    public void Eliminar(View view){

        String nom = nombreBuscar.getText().toString();
        db_query dbQuery = new db_query(this);
        int value = dbQuery.eliminarDatoParque(nom);

        if(value > 0 ){
            Toast.makeText(this, "Dato Eliminado", Toast.LENGTH_SHORT).show();
        }
    }

    public void Limpiar(View view){
        nombreBuscar.setText("");
        nombre.setText("");
        apellido.setText("");
        correo.setText("");
        pass.setText("");
    }
}