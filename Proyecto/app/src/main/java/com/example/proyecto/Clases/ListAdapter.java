package com.example.proyecto.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyecto.R;

import java.util.List;

// La unica funcion de esta clase es la de servir como adaptador para la lista que se vera en el fragment
public class ListAdapter extends ArrayAdapter<PlatillosClass> {
    private List<PlatillosClass> platillosClassList;
    private Context context;
    private int resourceLayout;

    public ListAdapter(@NonNull Context context, int resource, List<PlatillosClass> objects) {
        super(context, resource, objects);
        this.platillosClassList = objects;
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceLayout, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imgImagenPlatillo);
            holder.nombre = convertView.findViewById(R.id.txtNombrePlatillo);
            holder.costo = convertView.findViewById(R.id.txtCostoPlatillo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PlatillosClass platillosClass = platillosClassList.get(position);

        holder.imagen.setImageResource(platillosClass.getImagen());
        holder.nombre.setText(platillosClass.getNombre());
        holder.costo.setText(String.valueOf(platillosClass.getCosto()));

        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView costo;
    }
}
