package com.example.mislugares.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

import com.example.mislugares.Aplicacion;
import com.example.mislugares.R;
import com.example.mislugares.databinding.DetalleLugarBinding;
import com.example.mislugares.databinding.FragmentAnadirLugarBinding;
import com.example.mislugares.models.GeoPunto;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.TipoLugar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnadirLugar extends Fragment {
    private FragmentAnadirLugarBinding binding;
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
    String nombreImagen="";

    private ListaLugares listaLugares;

    public AnadirLugar() {
        // Required empty public constructor
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnadirLugarBinding.inflate(inflater, container, false);
        nombre = binding.getRoot().findViewById(R.id.textName);
        direccion = binding.getRoot().findViewById(R.id.textDireccion);
        longitud = binding.getRoot().findViewById(R.id.textLongitud);
        latitud = binding.getRoot().findViewById(R.id.textLatitud);
        url = binding.getRoot().findViewById(R.id.textUrl);
        comentario = binding.getRoot().findViewById(R.id.textComentario);
        fecha = binding.getRoot().findViewById(R.id.textFecha);
        valoracion = binding.getRoot().findViewById(R.id.barraValoracion);
        spinner = binding.getRoot().findViewById(R.id.tipoLugar);
        imagen = binding.getRoot().findViewById(R.id.imageView);
        botonAceptar = binding.getRoot().findViewById(R.id.btnAceptar);


        TipoLugar[] tiposLugar = TipoLugar.values();
        ArrayAdapter<TipoLugar> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, tiposLugar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TipoLugar tipoLugar = (TipoLugar) parent.getItemAtPosition(position);
                nombreImagen="";
                switch (tipoLugar) {
                    case MONTANIA:
                        imagen.setImageResource(R.drawable.montanas);
                        nombreImagen="montanas";
                        break;
                    case RIO:
                        imagen.setImageResource(R.drawable.rio);
                        nombreImagen="rio";
                        break;
                    case PLAYA:
                        imagen.setImageResource(R.drawable.barbadosbeach);
                        nombreImagen="barbadosbeach";
                        break;
                    case LAGO:
                        imagen.setImageResource(R.drawable.lago);
                        nombreImagen="lago";
                        break;
                    case ISLA:
                        imagen.setImageResource(R.drawable.islas);
                        nombreImagen="islas";
                        break;
                    case PRADO:
                        imagen.setImageResource(R.drawable.prado);
                        nombreImagen="prado";
                        break;
                    case VALLE:
                        imagen.setImageResource(R.drawable.valle);
                        nombreImagen="valle";
                        break;
                    case PUEBLO:
                        imagen.setImageResource(R.drawable.pueblo);
                        nombreImagen="pueblo";
                        break;
                    case CIUDAD:
                        imagen.setImageResource(R.drawable.catedral_sevilla);
                        nombreImagen="catedral_sevilla";
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fecha2 = LocalDate.parse(fecha.getText().toString(), formatter);
                Lugar lugar = new Lugar(nombre.getText().toString(), direccion.getText().toString(),
                        new GeoPunto(Double.valueOf(longitud.getText().toString()), Double.valueOf(latitud.getText().toString())),
                        nombreImagen, url.getText().toString(), comentario.getText().toString(),
                        fecha2, (double) valoracion.getRating(),
                        TipoLugar.valueOf(spinner.getSelectedItem().toString()));
                listaLugares= ((Aplicacion) requireActivity().getApplication()).listaLugares;
                listaLugares.añadirLugares(lugar);
                Toast.makeText(getContext(), "Se añadio correctamente", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(), "Se produjo un error", Toast.LENGTH_SHORT).show();
            }
            NavController navController = Navigation.findNavController(getActivity(),R.id.fragmentPrincipal);
            navController.navigate(R.id.vistaPrincipal);
        });
        return binding.getRoot();
    }
}