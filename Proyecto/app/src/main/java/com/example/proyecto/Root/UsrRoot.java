package com.example.proyecto.Root;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.proyecto.R;

public class UsrRoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr_root);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_overflow, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.ItRegistrarUsuario:

                Intent intent1 = new Intent(this,usuarios.class);
                startActivity(intent1);

                break;
              //  newGame();
               // return true;
            case R.id.itMostrarUsuarios:

                Intent intent = new Intent(this,MostrarUsuarios.class);
                startActivity(intent);
                break;
                //return true;


        }
        return super.onOptionsItemSelected(item);
    }
}