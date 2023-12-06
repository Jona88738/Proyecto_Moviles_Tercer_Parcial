package com.example.proyecto.ui.file;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Clases.CostruEnvio;
import com.example.proyecto.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileFragment extends Fragment {

    private FileViewModel mViewModel;
    private CostruEnvio costruenvios;
    private TextView HistorialEnvios;
    private Context context;

    public static FileFragment newInstance() {
        return new FileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_file, container, false);

        costruenvios = new CostruEnvio();
        context = getContext();

        HistorialEnvios = rootView.findViewById(R.id.editxtHistorialEnvios);

        StringBuilder textoAcumulado = new StringBuilder();

        try {
            for (int i = 0; i < 5; i++) {
                String textoArchivo = abrirArchivo(i);
                if (!textoArchivo.isEmpty()) {
                    textoAcumulado.append(textoArchivo).append('\n');
                }
            }
            HistorialEnvios.setText(textoAcumulado.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return rootView;
    }

    private String abrirArchivo(int contador) {
        String textoLeido = "";

        //Obtener la lista de los archivos internos de la aplicación
        String archivos[] = context.fileList();
        //Validar la existencia del archivo interno
        if (existeArchivo(archivos, "Envio" + contador + ".txt")) {
            try {
                //Objeto que asocia al archivo especificado, para lectura
                InputStreamReader archivoInterno = new InputStreamReader(context.openFileInput("Envio" + contador + ".txt"));
                //Objeto que relaciona el arhicov con el flujo de entrada (lectura)
                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
                //Lectura del archivo y colocar en una variable de tipo cadena
                String linea = leerArchivo.readLine();
                //Ciclo para leer el contenido del archivo
                while (linea != null) {
                    textoLeido += linea + '\n';
                    linea = leerArchivo.readLine();
                }
                //Cerrar el flujo del archivo
                leerArchivo.close();
                //Cerrar el archivo
                archivoInterno.close();
            } catch (IOException e) {
                Toast.makeText(getActivity(), "Error al leer el archivo.", Toast.LENGTH_SHORT).show();
            }
        }
        return textoLeido;
    }

    private boolean existeArchivo(String[] archivos, String s) {
        //Recorrido de la lista de nombres de archivos internos de la aplicación
        for (int i = 0; i < archivos.length; i++) {
            //Se busca el archivo especificado
            if (s.equals(archivos[i])) {
                //En caso de existir, termina el ciclo y retorna verdadero
                return true;
            }
        }
        //En caso de no existir ya recorrido el archivo de archivos, retorna falso
        return false;
    }

}