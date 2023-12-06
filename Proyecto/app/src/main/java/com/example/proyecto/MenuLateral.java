package com.example.proyecto;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.proyecto.Clases.BtnNoti;
import com.example.proyecto.ui.historial.HistorialFragment;
import com.example.proyecto.Clases.Carrito;
import com.example.proyecto.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.databinding.ActivityMenuLateralBinding;

public class MenuLateral extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuLateralBinding binding;

    private Carrito[] carrito;

    public Carrito[] getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito[] carrito) {
        this.carrito = carrito;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuLateralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuLateral.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.historialFragment, R.id.envioFragment,R.id.mapsFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_lateral);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        if(BtnNoti.puerta  == 2){

            //Toast.makeText(this,"XD",Toast.LENGTH_LONG).show();
            notificationManagerCompat.cancel(HistorialFragment.NOTIFICACION_ID);

            FragmentTransaction t = getSupportFragmentManager().beginTransaction(); // getParentFragmentManager().beginTransaction();
            t.replace(R.id.Constraint_hom,new HistorialFragment());
            t.commit();

            HomeFragment.CLprincipal.setVisibility(View.GONE);

            Toast.makeText(this,"Realizo su compra",Toast.LENGTH_LONG).show();
            notificationManagerCompat.cancel(HistorialFragment.NOTIFICACION_ID);

            HistorialFragment.costruCuenta.setAumento(HistorialFragment.costruCuenta.getAumento() - HistorialFragment.precTotal-ActivityCupones.Descuento);

           // Toast.makeText(this,"vacio",Toast.LENGTH_LONG).show();
        }else if(BtnNoti.puerta == 3){

            Toast.makeText(this,"Su compra fue cancelada",Toast.LENGTH_LONG).show();
            notificationManagerCompat.cancel(HistorialFragment.NOTIFICACION_ID);

             HistorialFragment.NOTIFICACION_ID = 0;

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_lateral);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Exit){
            Intent exit = new Intent(this, SalidaActivity.class);
            startActivity(exit);
        }
        return super.onOptionsItemSelected(item);
    }

    public void miMetodo(){

    }




   public void onDestroy() {
       Toast.makeText(this,"Murio",Toast.LENGTH_LONG).show();

       Log.d("Murio", "xD");

       super.onDestroy();
   }

}