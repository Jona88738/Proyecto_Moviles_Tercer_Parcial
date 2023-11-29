package com.example.proyecto.ui.historial;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.proyecto.databinding.FragmentHistorialBinding;

public class HistorialFragment extends Fragment {

    private @NonNull FragmentHistorialBinding mViewModel;
    private TextView txtCantidadEnchiladas, txtCantidadFlautas, txtCantidadPozole,
            txtCantidadTacos, txtCantidadTortas, txtPrecioTotal;
    private Button btnPagar;
    private Carrito[] carrito;
    private CostruCuenta costruCuenta;
    private Double cantidad_total = 0.0;
    private int cantidad_comida_pozole = 0, cantidad_comida_tacos = 0, cantidad_comida_tortas = 0,
                cantidad_comida_flautas = 0, cantidad_comida_enchiladas = 0;
    private Double precio_comida_pozole = 0.0, precio_comida_tacos = 0.0, precio_comida_tortas = 0.0,
            precio_comida_flautas = 0.0, precio_comida_enchiladas = 0.0;

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
                Toast.makeText(getActivity(), "Gracias por tu compra!", Toast.LENGTH_SHORT).show();
                int precTotal = PrecioTotal.intValue();
                costruCuenta.setAumento(costruCuenta.getAumento() - precTotal);
                txtCantidadEnchiladas.setText("0"); // Mostramos el indicador como 0
                txtCantidadFlautas.setText("0");    // Mostramos el indicador como 0
                txtCantidadPozole.setText("0");     // Mostramos el indicador como 0
                txtCantidadTacos.setText("0");      // Mostramos el indicador como 0
                txtCantidadTortas.setText("0");     // Mostramos el indicador como 0
                txtPrecioTotal.setText("0");        // Mostramos el indicador como 0
                carrito = null;     // Borramos el arreglo.
            }
        } else {
            Toast.makeText(getActivity(), "Por favor fondea tu cuenta", Toast.LENGTH_SHORT).show();
        }
    }

}