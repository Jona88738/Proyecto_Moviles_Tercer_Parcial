package com.example.proyecto.ui.gallery;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyecto.Clases.CostruCuenta;
import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private EditText mon;
    private TextView res;
    private CostruCuenta costrucuenta;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        costrucuenta = new CostruCuenta();

        mon = root.findViewById(R.id.editTextNumberDecimal);
        res = root.findViewById(R.id.txtdinero);
        Button buttonMostrarValor = root.findViewById(R.id.subir);

        if(costrucuenta != null){
            res.setText(String.valueOf(costrucuenta.getAumento()));
        } else {
            res.setText(String.valueOf(0));
        }

        buttonMostrarValor.setOnClickListener(v -> {
            if (camposLlenos()) {
                obtenervalor();
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean camposLlenos() {
        return !TextUtils.isEmpty(mon.getText());
    }

    public void obtenervalor(){
        int pre = Integer.parseInt(mon.getText().toString());
        int antigua_cuenta = 0;
        mon.setText("");
        if(costrucuenta != null){
            pre = pre + costrucuenta.getAumento();
        }
        costrucuenta.setAumento(pre);
        res.setText(String.valueOf(pre));
    }
}