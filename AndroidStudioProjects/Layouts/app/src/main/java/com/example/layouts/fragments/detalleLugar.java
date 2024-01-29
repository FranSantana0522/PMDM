package com.example.layouts.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.layouts.R;
import com.example.layouts.models.Lugar;
import com.example.layouts.models.TipoLugar;

import java.util.ArrayList;


public class detalleLugar extends Fragment {
    private Spinner spinner;
    private ImageView imagen;
    private EditText nombre;
    private EditText direccion;
    private EditText longitud;
    private EditText latitud;
    private EditText url;
    private EditText comentario;
    private EditText fecha;
    private RatingBar valoracion;
    private Button botonAceptar;
    private Lugar lugar;

    public detalleLugar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_lugar, container, false);
        nombre = view.findViewById(R.id.textName);
        direccion = view.findViewById(R.id.textDireccion);
        longitud = view.findViewById(R.id.textLongitud);
        latitud = view.findViewById(R.id.textLatitud);
        url = view.findViewById(R.id.textUrl);
        comentario = view.findViewById(R.id.textComentario);
        fecha = view.findViewById(R.id.textFecha);
        valoracion = view.findViewById(R.id.barraValoracion);
        spinner = view.findViewById(R.id.tipoLugar);
        imagen = view.findViewById(R.id.imageView);
        botonAceptar = view.findViewById(R.id.btnAceptar);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("lugar")) {
            lugar = bundle.getParcelable("lugar");
            nombre.setText(lugar.getNombre());
            direccion.setText(lugar.getDireccion());
            longitud.setText(lugar.getInfoLugar().getLongitud().toString());
            latitud.setText(lugar.getInfoLugar().getLatitud().toString());
            url.setText(lugar.getUrl());
            comentario.setText(lugar.getComentario());
            fecha.setText(lugar.getFecha().toString());
            valoracion.setRating(lugar.getValoracion().floatValue());

            ArrayList<TipoLugar> tiposLugarList = new ArrayList<>();
            tiposLugarList.add(lugar.getTipoLugar());
            ArrayAdapter<TipoLugar> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, tiposLugarList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            String nombreImagen = lugar.getImagen();
            int[] imagenesResource = {R.drawable.barbadosbeach, R.drawable.catedral_sevilla, R.drawable.islas,R.drawable.lago
            ,R.drawable.montanas,R.drawable.prado,R.drawable.pueblo,R.drawable.rio,R.drawable.valle};
            for (int imagenResource : imagenesResource) {
                String nombreRecurso = getResources().getResourceEntryName(imagenResource);
                if (nombreImagen.equals(nombreRecurso)) {
                    imagen.setImageResource(imagenResource);
                }
            }

        }
        return view;
    }
}
