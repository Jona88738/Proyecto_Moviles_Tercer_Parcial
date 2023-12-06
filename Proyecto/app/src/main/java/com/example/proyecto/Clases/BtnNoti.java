package com.example.proyecto.Clases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.proyecto.MenuLateral;
import com.example.proyecto.ui.historial.HistorialFragment;

public class BtnNoti extends BroadcastReceiver {

    public static int puerta = 0;
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action != null) {
            if (action.equals("ACTION_BUTTON_1")) {

                intent =new Intent(context, MenuLateral.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agregar el FLAG_ACTIVITY_NEW_TASK

                context.startActivity(intent);

                puerta = 2;

                // El PendingIntent del primer botón fue activado
            } else if (action.equals("ACTION_BUTTON_2")) {

                intent =new Intent(context, MenuLateral.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agregar el FLAG_ACTIVITY_NEW_TASK

                context.startActivity(intent);

                puerta = 3;
                Log.d("MiReceiver", "El PendingIntent fue activado");


                //Toast.makeText(this.,"Cancelo su compra",Toast.LENGTH_LONG).show();
              //  notificationManagerCompat.cancel(HistorialFragment.NOTIFICACION_ID);

            }
                // El PendingIntent del segundo botón fue activado
            }
        Log.d("MiReceiver", "El PendingIntent fue activado");


    }
}
