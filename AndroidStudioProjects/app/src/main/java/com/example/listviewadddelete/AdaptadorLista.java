package com.example.listviewadddelete;

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

public class AdaptadorLista extends ArrayAdapter<Persona> {
    public AdaptadorLista(@NonNull Context context, ArrayList<Persona> listaP) {
        super(context, R.layout.list_item,listaP);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Persona persona=getItem(position);
        if (view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ImageView imagenLista=view.findViewById(R.id.listImage);
        TextView nombre=view.findViewById(R.id.listaNombre);

        imagenLista.setImageResource(persona.imagen);
        nombre.setText(persona.nombre);

        return view;
    }
}
