package com.example.ex2ev1curso2023franciscojsantanamontes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Producto> {
    private Context mContext;
    private int mResource;

    public Adaptador(Context context, int resource, ArrayList<Producto> productos) {
        super(context, resource, productos);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        Producto producto = getItem(position);

        if (producto != null) {
            ImageView imagen = convertView.findViewById(R.id.imageViewProducto);
            TextView nombre = convertView.findViewById(R.id.textViewNombre);
            TextView precio = convertView.findViewById(R.id.textViewPrecio);

            if (imagen != null) {
                imagen.setImageResource(producto.getImagen());
            }
            if (nombre != null) {
                nombre.setText(producto.getNombre());
            }
            if (precio != null) {
                precio.setText(String.valueOf(producto.getPrecio()));
            }
        }

        return convertView;
    }
}
