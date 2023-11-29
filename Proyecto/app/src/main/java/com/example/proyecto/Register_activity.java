package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.Clases.Usuario;
import com.example.proyecto.db.db_queryUsuario;

public class Register_activity extends AppCompatActivity {
    private EditText nombre, apellido, correo, contrasena, confirmarContra;
    Usuario user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.editxtName);
        apellido = findViewById(R.id.editxtLastName);
        correo = findViewById(R.id.editxtEmail);
        contrasena = findViewById(R.id.editxtPassword);
        confirmarContra = findViewById(R.id.etConfirmPassword);
        user = (Usuario) getIntent().getSerializableExtra("usuarios");
    }

    public void registrarse(View view) {





        String name = nombre.getText().toString();
        String lastName = apellido.getText().toString();
        String email = correo.getText().toString();
        String password = contrasena.getText().toString();
        String confirm = confirmarContra.getText().toString();





        if(name.equals("")  || lastName.equals("") || email.equals("") || password.equals("") || confirm.equals("")){
            Toast mensaje = Toast.makeText(this, "Rellene todos los campos ", Toast.LENGTH_LONG);
            mensaje.show();
        }else{
            if (password.equals(confirm)) {

                db_queryUsuario db_query = new db_queryUsuario(this);
                long resul =  db_query.insertUsuario(name,lastName,email,password);


                if(resul > 0){
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Register_activity.this, MainActivity.class);

             startActivity(intent);
          }





            } else {
                Toast mensaje = Toast.makeText(this, "Las contraseña no coinciden", Toast.LENGTH_LONG);
                mensaje.show();
            }
        }



    }

    public void close( View view){
        Intent intent = new Intent(Register_activity.this, MainActivity.class);
        intent.putExtra("usuarios", user);
        startActivity(intent);
    }
}