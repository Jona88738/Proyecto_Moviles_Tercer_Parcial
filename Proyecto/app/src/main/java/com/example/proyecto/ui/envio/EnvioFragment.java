package com.example.proyecto.ui.envio;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto.Clases.CostruEnvio;
import com.example.proyecto.R;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class EnvioFragment extends Fragment {


    private EditText ca, co, ciu, cod;
    private Spinner dom;
    private String[] opcionesSpinner = {"DOMICILIO", "CETI COLOMOS", "CETI TONALA", "CETI RIO SANTIAGO"};
    private CostruEnvio costruenvios;
    private int cont = 0;
    private Context context;

    private EnvioViewModel mViewModel;

    public static EnvioFragment newInstance() {
        return new EnvioFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EnvioViewModel.class);
        // TODO: Use the ViewModel
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_envio, container, false);

        ca = rootView.findViewById(R.id.txtcalle);
        co = rootView.findViewById(R.id.txtcolonia);
        ciu = rootView.findViewById(R.id.txtciudad);
        cod = rootView.findViewById(R.id.txtpostal);
        dom = rootView.findViewById(R.id.sporden);

        costruenvios = new CostruEnvio();
        context = requireContext();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_spinner_item, opcionesSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dom.setAdapter(adapter);

        Button btnEnviar = rootView.findViewById(R.id.btnGuardarDomicilio2);

        btnEnviar.setOnClickListener(v -> {
            if (camposLlenos()) {
                enviar();
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private boolean camposLlenos() {
        return !TextUtils.isEmpty(ca.getText())
                && !TextUtils.isEmpty(co.getText())
                && !TextUtils.isEmpty(ciu.getText())
                && !TextUtils.isEmpty(cod.getText());
    }

    public void enviar() {
        String ord = "", cal = "", col = "", ciud = "";
        int post = 0;

        ord = dom.getSelectedItem().toString();
        cal = ca.getText().toString();
        col = co.getText().toString();
        ciud = ciu.getText().toString();
        post = Integer.parseInt(cod.getText().toString());

        guardarArchivo(ord, cal, col, ciud, post);
        cont++;
        Limpiar();
    }

    public void Limpiar(){
        dom.setSelection(0);
        ca.setText("");
        co.setText("");
        ciu.setText("");
        cod.setText("");
    }

    private void guardarArchivo(String Domicilio, String Calle, String Colonia, String ciudad, int cod_postal) {
        try {
            // Objeto que asocia al archivo especificado, para escritura
            OutputStreamWriter archivoInterno = new OutputStreamWriter(context.openFileOutput("Envio"+cont+".txt", Activity.MODE_PRIVATE));

            // Guardar información adicional
            String informacion = "\nDomicilio: " + Domicilio +
                    "\n\nCalle: " + Calle +
                    "\n\nColonia: " + Colonia +
                    "\n\nCiudad: " + ciudad +
                    "\n\nCodigo Postal: " + cod_postal;
            archivoInterno.write(informacion);

            if(Domicilio == "DOMICILIO" || Calle == "" || Colonia == "" || ciudad == ""){
                Toast.makeText(getActivity(), "Llena todos los espacios", Toast.LENGTH_SHORT).show();
            } else {
                costruenvios = new CostruEnvio(Domicilio, Calle, Colonia, ciudad, cod_postal);
            }
            // Limpiar el buffer
            archivoInterno.flush();
            // Cerrar archivo
            archivoInterno.close();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Error al escribir en el archivo.", Toast.LENGTH_SHORT).show();
        }
        // Notificación de información almacenada
        Toast.makeText(getActivity(), "Envio "+cont+" alamcenado exitosamente.", Toast.LENGTH_SHORT).show();
    }
}