package com.example.proyecto.ui.historial;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
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

import com.example.proyecto.Clases.Carrito;
import com.example.proyecto.Clases.CostruCuenta;
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
    private CostruCuenta costruCuenta;
    private Double cantidad_total = 0.0;
    private int cantidad_comida_pozole = 0, cantidad_comida_tacos = 0, cantidad_comida_tortas = 0,
                cantidad_comida_flautas = 0, cantidad_comida_enchiladas = 0;
    private Double precio_comida_pozole = 0.0, precio_comida_tacos = 0.0, precio_comida_tortas = 0.0,
            precio_comida_flautas = 0.0, precio_comida_enchiladas = 0.0;
    private PendingIntent confirmar;
    private PendingIntent cancelar;
    private final static String CHANNEL_ID = "Notificacion";
    public final static int NOTIFICACION_ID = 0;

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
                    int precTotal = PrecioTotal.intValue();
                    costruCuenta.setAumento(costruCuenta.getAumento() - precTotal);
                    carrito = null;     // Borramos el arreglo.
                } else {
                    carrito = null;
                }
            }
        } else {
            Toast.makeText(getActivity(), "Por favor fondea tu cuenta", Toast.LENGTH_SHORT).show();
        }
    }

    //Notificaciones
    private void setConfirmar() {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(requireActivity().getApplicationContext());
        stackBuilder.addParentStack(MenuLateral.class);
        //confirmar = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT);
        reiniciarValores();
        pagarCuenta = true;
    }

    private void setCancelar() {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(requireActivity().getApplicationContext());
        stackBuilder.addParentStack(MenuLateral.class);
        //cancelar = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT);
        reiniciarValores();
        pagarCuenta = false;
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


        builder.addAction(R.drawable.logo, "Confirmar", confirmar);
        builder.addAction(R.drawable.logo, "Cancelar", cancelar);

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
        Toast.makeText(getActivity(), "Gracias por tu compra!", Toast.LENGTH_SHORT).show();
    }
}