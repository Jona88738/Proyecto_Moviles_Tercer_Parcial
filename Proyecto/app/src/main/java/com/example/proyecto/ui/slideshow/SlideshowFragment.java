package com.example.proyecto.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyecto.Clases.Carrito;
import com.example.proyecto.Clases.ListAdapter;
import com.example.proyecto.MenuLateral;
import com.example.proyecto.Clases.PlatillosClass;
import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements AdapterView.OnItemClickListener {

    private FragmentSlideshowBinding binding;
    private ListView listView;
    private ListAdapter listAdapter;
    private List<PlatillosClass> mPlatillos = new ArrayList<>();
    private int contador = 0;
    private Carrito[] carrito;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        carrito = new Carrito[7];

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView = binding.listPlatillos;
        listView.setOnItemClickListener(this);

        mPlatillos.add(new PlatillosClass(R.mipmap.pozole,"Pozole",104.39));
        mPlatillos.add(new PlatillosClass(R.mipmap.tacos,"Tacos",16.82));
        mPlatillos.add(new PlatillosClass(R.mipmap.tortas,"Tortas",75.98));
        mPlatillos.add(new PlatillosClass(R.mipmap.flautas,"Flautas",15.07));
        mPlatillos.add(new PlatillosClass(R.mipmap.enchiladas,"Enchiladas",52.49));

        // Inicializador de adaptador
        listAdapter = new ListAdapter(requireContext(), R.layout.item_row, mPlatillos);

        listView.setAdapter(listAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("---- Contador INICIO: " + contador);
        if (i == 0) {
            System.out.println("****** Elemento clickeado: " + i);
            carrito[contador] = new Carrito(contador, i, 1, 89.99, 104.39);
            contador++; // Incrementar contador solo cuando se almacena un elemento en carrito
            Toast.makeText(getActivity(), "Elemento seleccionado: Pozole", Toast.LENGTH_SHORT).show();
        } else if (i == 1) {
            System.out.println("****** Elemento clickeado: " + i);
            carrito[contador] = new Carrito(contador, i, 1, 14.50, 16.82);
            contador++; // Incrementar contador solo cuando se almacena un elemento en carrito
            Toast.makeText(getActivity(), "Elemento seleccionado: Tacos", Toast.LENGTH_SHORT).show();
        } else if (i == 2) {
            System.out.println("****** Elemento clickeado: " + i);
            carrito[contador] = new Carrito(contador, i, 1, 65.5, 75.98);
            contador++; // Incrementar contador solo cuando se almacena un elemento en carrito
            Toast.makeText(getActivity(), "Elemento seleccionado: Tortas", Toast.LENGTH_SHORT).show();
        } else if (i == 3) {
            System.out.println("****** Elemento clickeado: " + i);
            carrito[contador] = new Carrito(contador, i, 1, 12.99, 15.07);
            contador++; // Incrementar contador solo cuando se almacena un elemento en carrito
            Toast.makeText(getActivity(), "Elemento seleccionado: Flautas", Toast.LENGTH_SHORT).show();
        } else if (i == 4) {
            System.out.println("****** Elemento clickeado: " + i);
            carrito[contador] = new Carrito(contador, i, 1, 45.25, 52.49);
            contador++; // Incrementar contador solo cuando se almacena un elemento en carrito
            Toast.makeText(getActivity(), "Elemento seleccionado: Enchiladas", Toast.LENGTH_SHORT).show();
        }

        System.out.println("---- Contador Actual: " + contador);

        ((MenuLateral) requireActivity()).setCarrito(carrito);

    }
}