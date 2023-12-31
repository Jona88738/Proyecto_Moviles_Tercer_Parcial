package com.example.proyecto.ui.historial;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.icu.text.ListFormatter.Type.OR;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.ActivityCupones;
import com.example.proyecto.Clases.BtnNoti;
import com.example.proyecto.Clases.Carrito;
import com.example.proyecto.Clases.CostruCuenta;
import com.example.proyecto.MainActivity;
import com.example.proyecto.MenuLateral;
import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentHistorialBinding;

public class HistorialFragment extends Fragment {

    private @NonNull FragmentHistorialBinding mViewModel;
    private TextView txtCantidadEnchiladas;
    private TextView txtCantidadFlautas;
    private TextView txtCantidadPozole;
    private TextView txtCantidadTacos;
    private TextView txtCantidadTortas;
    private TextView txtPrecioTotal;
    private Button btnPagar;
    private Carrito[] carrito;
    public static CostruCuenta costruCuenta;
    private Double cantidad_total = 0.0;
    private int cantidad_comida_pozole = 0, cantidad_comida_tacos = 0, cantidad_comida_tortas = 0,
                cantidad_comida_flautas = 0, cantidad_comida_enchiladas = 0;
    private Double precio_comida_pozole = 0.0, precio_comida_tacos = 0.0, precio_comida_tortas = 0.0,
            precio_comida_flautas = 0.0, precio_comida_enchiladas = 0.0;

    Intent intentConfirmar;
    Intent intentCancelar;
    private PendingIntent confirmar;
    private PendingIntent cancelar;
    private final static String CHANNEL_ID = "Notificacion";
    public  static int NOTIFICACION_ID = 0;

    public  static int opcion = 0;
    private boolean pagarCuenta = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = FragmentHistorialBinding.inflate(inflater, container, false);
        View root = mViewModel.getRoot();
        
        costruCuenta = new CostruCuenta();
        
        txtCantidadEnchiladas = mViewModel.txtCantidadEnchiladas;
        txtCantidadFlautas = mViewModel.txtCantidadFlautas;
        txtCantidadPozole = mViewModel.txtCantidadPozole;
        txtCantidadTacos = mViewModel.txtCantidadTacos;
        txtCantidadTortas = mViewModel.txtCantidadTortas;
        txtPrecioTotal = mViewModel.txtPrecioTotal;
        btnPagar = mViewModel.btnPay;

        carrito = ((MenuLateral) requireActivity()).getCarrito();
        // Funciones para mostrar los elementos
        try {
            for (int i = 0; i < 7; i++) {
                if (carrito[i] != null) {
                    if(carrito[i].getId_platillo() == 0){
                        cantidad_comida_pozole++;
                        precio_comida_pozole = carrito[i].getPrecio_IVA();
                    } else if(carrito[i].getId_platillo() == 1){
                        cantidad_comida_tacos++;
                        precio_comida_tacos = carrito[i].getPrecio_IVA();
                    } else if(carrito[i].getId_platillo() == 2){
                        cantidad_comida_tortas++;
                        precio_comida_tortas = carrito[i].getPrecio_IVA();
                    } else if(carrito[i].getId_platillo() == 3){
                        cantidad_comida_flautas++;
                        precio_comida_flautas = carrito[i].getPrecio_IVA();
                    } else if(carrito[i].getId_platillo() == 4){
                        cantidad_comida_enchiladas++;
                        precio_comida_enchiladas = carrito[i].getPrecio_IVA();
                    }

                    txtCantidadEnchiladas.setText(String.valueOf(cantidad_comida_enchiladas));
                    txtCantidadFlautas.setText(String.valueOf(cantidad_comida_flautas));
                    txtCantidadPozole.setText(String.valueOf(cantidad_comida_pozole));
                    txtCantidadTacos.setText(String.valueOf(cantidad_comida_tacos));
                    txtCantidadTortas.setText(String.valueOf(cantidad_comida_tortas));

                    cantidad_total =
                            (cantidad_comida_pozole * precio_comida_pozole) +
                            (cantidad_comida_tacos * precio_comida_tacos) +
                            (cantidad_comida_tortas * precio_comida_tortas) +
                            (cantidad_comida_flautas * precio_comida_flautas) +
                            (cantidad_comida_enchiladas * precio_comida_enchiladas);

                    txtPrecioTotal.setText(String.format("%.2f", cantidad_total));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        // Metodo Onclick
        btnPagar.setOnClickListener(v -> PagarCuenta(cantidad_total));

        return root;
    }

    public static int precTotal;
    private void PagarCuenta(Double PrecioTotal) {

        if(costruCuenta != null){
            if(PrecioTotal > costruCuenta.getAumento()){
                Toast.makeText(getActivity(), "No tienes suficente dinero", Toast.LENGTH_SHORT).show();
            } else {

                setConfirmar();
                setCancelar();
                crearCanalNotificacion();
                crearNotificacion();
                if(pagarCuenta) {

                    if(ActivityCupones.isValido) {
                       // int precTotal = PrecioTotal.intValue();
                         precTotal = PrecioTotal.intValue();

                        costruCuenta.setAumento(costruCuenta.getAumento() - (precTotal - ActivityCupones.Descuento));
                        reiniciarValores();
                        Toast.makeText(getActivity(), "Gracias por tu compra!", Toast.LENGTH_SHORT).show();
                        carrito = null;     // Borramos el arreglo.
                    } else {
                        //int precTotal = PrecioTotal.intValue();
                         precTotal = PrecioTotal.intValue();

                        costruCuenta.setAumento(costruCuenta.getAumento() - precTotal);
                        reiniciarValores();
                        Toast.makeText(getActivity(), "Gracias por tu compra!", Toast.LENGTH_SHORT).show();
                        carrito = null;     // Borramos el arreglo.
                    }
                } else {
                    precTotal = PrecioTotal.intValue();

                    Toast.makeText(getActivity(),"reinicio",Toast.LENGTH_LONG).show();

                    reiniciarValores();
                    carrito = null;
                }
            }
        } else {
            Toast.makeText(getActivity(), "Por favor fondea tu cuenta", Toast.LENGTH_SHORT).show();
        }
    }

    //Notificaciones
    PendingIntent pendingintentSi,Aux2;
    private void setConfirmar() {

        Intent intent = new Intent(getActivity(), BtnNoti.class);

        intent.setAction("ACTION_BUTTON_1");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(getActivity());
        stackBuilder.addNextIntent(intent);

       //pendingintentSi = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);
        Aux2 = PendingIntent.getBroadcast(getActivity(),0,intent, PendingIntent.FLAG_IMMUTABLE );// stackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);


       /*

            Checar bien la confirmacion

        */

        opcion = 1;
    }

    PendingIntent pendingIntentNO,Aux;

    private void setCancelar() {

        Intent intent2 = new Intent(getActivity(), BtnNoti.class);

        intent2.setAction("ACTION_BUTTON_2");

        Aux = PendingIntent.getBroadcast(getActivity(),0,intent2, PendingIntent.FLAG_IMMUTABLE);// stackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(getActivity());
        stackBuilder.addNextIntent(intent2);

        //pendingIntentNO = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE);

       // .getBroadcast
        opcion = 2;
    }

    public void crearCanalNotificacion() {
        CharSequence nombre = "Notificaciones";
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, nombre, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public void crearNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity().getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle("¡Order");
        builder.setContentText("¿Deseas confirmar tu cuenta?");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setVibrate(new long[]{50,50,50,50,50});
        builder.setDefaults(Notification.DEFAULT_SOUND);


        builder.addAction(R.drawable.logo, "Confirmar",Aux2);
        builder.addAction(R.drawable.logo, "Cancelar",Aux);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(requireActivity().getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());


    }
    private void reiniciarValores() {
        txtCantidadEnchiladas.setText("0"); // Mostramos el indicador como 0
        txtCantidadFlautas.setText("0");    // Mostramos el indicador como 0
        txtCantidadPozole.setText("0");     // Mostramos el indicador como 0
        txtCantidadTacos.setText("0");      // Mostramos el indicador como 0
        txtCantidadTortas.setText("0");     // Mostramos el indicador como 0
        txtPrecioTotal.setText("0");        // Mostramos el indicador como 0
    }
}