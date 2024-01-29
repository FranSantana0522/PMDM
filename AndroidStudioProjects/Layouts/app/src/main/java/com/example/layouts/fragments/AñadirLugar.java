package com.example.layouts.fragments;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.layouts.Aplicacion;
import com.example.layouts.R;
import com.example.layouts.models.GeoPunto;
import com.example.layouts.models.ListaLugares;
import com.example.layouts.models.Lugar;
import com.example.layouts.models.TipoLugar;

import java.time.LocalDate;
import java.util.Date;

public class AñadirLugar extends Fragment {

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

    private ListaLugares listaLugares;

    public AñadirLugar() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.anadir_lugar, container, false);

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

        TipoLugar[] tiposLugar = TipoLugar.values();
        ArrayAdapter<TipoLugar> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, tiposLugar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TipoLugar tipoLugar = (TipoLugar) parent.getItemAtPosition(position);
                switch (tipoLugar) {
                    case MONTANIA:
                        imagen.setImageResource(R.drawable.montanas);
                        break;
                    case RIO:
                        imagen.setImageResource(R.drawable.rio);
                        break;
                    case PLAYA:
                        imagen.setImageResource(R.drawable.barbadosbeach);
                        break;
                    case LAGO:
                        imagen.setImageResource(R.drawable.lago);
                        break;
                    case ISLA:
                        imagen.setImageResource(R.drawable.islas);
                        break;
                    case PRADO:
                        imagen.setImageResource(R.drawable.prado);
                        break;
                    case VALLE:
                        imagen.setImageResource(R.drawable.valle);
                        break;
                    case PUEBLO:
                        imagen.setImageResource(R.drawable.pueblo);
                        break;
                    case CIUDAD:
                        imagen.setImageResource(R.drawable.catedral_sevilla);
                    default:

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acciones cuando no se selecciona nada (opcional)
            }
        });
        botonAceptar.setOnClickListener(vista->{
            try {
                Lugar lugar= new Lugar(nombre.getText().toString(),direccion.getText().toString(),
                        new GeoPunto(Double.valueOf(longitud.getText().toString()),Double.valueOf(latitud.getText().toString())),
                        imagen.getDrawable().toString(),url.getText().toString(),comentario.getText().toString(),
                        LocalDate.parse(fecha.getText().toString()),Double.valueOf(valoracion.toString()),
                        TipoLugar.valueOf(spinner.toString()));
                listaLugares= ((Aplicacion) requireActivity().getApplication()).listaLugares;
                listaLugares.añadirLugares(lugar);
                Toast.makeText(getContext(), "Se añadio correctamente", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(), "Se produjo un error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}



