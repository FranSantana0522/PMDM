package com.example.mislugares.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mislugares.Aplicacion;
import com.example.mislugares.R;
import com.example.mislugares.databinding.DetalleLugarBinding;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.TipoLugar;

import java.util.ArrayList;

public class detalleLugar extends Fragment {

    private DetalleLugarBinding binding;
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
    private Lugar lugar;
    private Button borrar;
    private ListaLugares listaLugares;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = DetalleLugarBinding.inflate(inflater, container, false);
        nombre =  binding.getRoot().findViewById(R.id.textName);
        direccion = binding.getRoot().findViewById(R.id.textDireccion);
        longitud = binding.getRoot().findViewById(R.id.textLongitud);
        latitud = binding.getRoot().findViewById(R.id.textLatitud);
        url = binding.getRoot().findViewById(R.id.textUrl);
        comentario = binding.getRoot().findViewById(R.id.textComentario);
        fecha = binding.getRoot().findViewById(R.id.textFecha);
        valoracion = binding.getRoot().findViewById(R.id.barraValoracion);
        spinner = binding.getRoot().findViewById(R.id.tipoLugar);
        imagen = binding.getRoot().findViewById(R.id.imageView);
        borrar = binding.getRoot().findViewById(R.id.borrar);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("lugar")) {
            lugar = (Lugar) bundle.getSerializable("lugar");
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
        borrar.setOnClickListener(view->{
            listaLugares = ((Aplicacion) requireActivity().getApplication()).listaLugares;
            listaLugares.borrarLugar(lugar);
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    /*    binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(detalleLugar.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });   */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}