package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.proyecto.Clases.Usuario;
import com.example.proyecto.db.db_queryUsuario;

import java.io.IOException;

public class Register_activity extends AppCompatActivity {
    private EditText nombre, apellido, correo, contrasena, confirmarContra;
    Usuario user;

    MediaPlayer mediaPlayer;
     public  static ToggleButton btnMusica;
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

        btnMusica = findViewById(R.id.tbMusica);

        btnMusica.setChecked(true);
        inicioCancion();
        btnMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Uri uri = Uri.parse("android.resource://" +
                            getPackageName() + "/" + R.raw.agua);
                    try {
                        mediaPlayer.setDataSource(Register_activity.this,
                                uri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(Register_activity.this, "Comienza reproducción ", Toast.LENGTH_LONG).show();



                    } catch (IOException e){
                        Toast.makeText(Register_activity.this, "Error al reproducir",Toast.LENGTH_LONG).show();


                    }
                    // The toggle is enabled
                } else {

                    if(mediaPlayer != null && mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                        mediaPlayer = null;

                        Toast.makeText(Register_activity.this, "Se detiene reproducción ",Toast.LENGTH_LONG).show();


                    }//if

                    // The toggle is disabled
                }
            }
        });
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
    public void inicioCancion(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Uri uri = Uri.parse("android.resource://" +
                getPackageName() + "/" + R.raw.agua);
        try {
            mediaPlayer.setDataSource(Register_activity.this,
                    uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(Register_activity.this, "Comienza reproducción ", Toast.LENGTH_LONG).show();



        } catch (IOException e){
            Toast.makeText(Register_activity.this, "Error al reproducir",Toast.LENGTH_LONG).show();


        }
    }

    public void close( View view){
        Intent intent = new Intent(Register_activity.this, MainActivity.class);
        intent.putExtra("usuarios", user);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
       // finish();
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = null;

            Toast.makeText(Register_activity.this, "Se detiene reproducción ", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this,"pause",Toast.LENGTH_LONG).show();
        super.onPause();
        finish();

    }
}