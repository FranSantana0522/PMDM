package com.example.mislugares.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
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
import com.example.mislugares.databinding.EditarLugarBinding;
import com.example.mislugares.databinding.FragmentAnadirLugarBinding;
import com.example.mislugares.models.GeoPunto;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.TipoLugar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class EditarLugar extends Fragment {
    private EditarLugarBinding binding;
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
    private Button botonAceptar;
    private ImageView camera;
    String nombreImagen="";

    private ListaLugares listaLugares;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public EditarLugar() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = EditarLugarBinding.inflate(inflater, container, false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
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
            camera = binding.getRoot().findViewById(R.id.camara);
            botonAceptar = binding.getRoot().findViewById(R.id.btnAceptar);
            Bundle bundle = getArguments();
            if (bundle != null && bundle.containsKey("editarLugar")) {
                lugar = (Lugar) bundle.getSerializable("editarLugar");
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
                int[] imagenesResource = {R.drawable.barbadosbeach, R.drawable.catedral_sevilla, R.drawable.islas, R.drawable.lago
                        , R.drawable.montanas, R.drawable.prado, R.drawable.pueblo, R.drawable.rio, R.drawable.valle};
                for (int imagenResource : imagenesResource) {
                    String nombreRecurso = getResources().getResourceEntryName(imagenResource);
                    if (nombreImagen.equalsIgnoreCase(nombreRecurso)) {
                        imagen.setImageResource(imagenResource);
                    }
                }

            }

            TipoLugar[] tiposLugar = TipoLugar.values();
            ArrayAdapter<TipoLugar> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, tiposLugar);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TipoLugar tipoLugar = (TipoLugar) parent.getItemAtPosition(position);
                    nombreImagen = "";
                    switch (tipoLugar) {
                        case MONTANIA:
                            imagen.setImageResource(R.drawable.montanas);
                            nombreImagen = "montanas";
                            break;
                        case RIO:
                            imagen.setImageResource(R.drawable.rio);
                            nombreImagen = "rio";
                            break;
                        case PLAYA:
                            imagen.setImageResource(R.drawable.barbadosbeach);
                            nombreImagen = "barbadosbeach";
                            break;
                        case LAGO:
                            imagen.setImageResource(R.drawable.lago);
                            nombreImagen = "lago";
                            break;
                        case ISLA:
                            imagen.setImageResource(R.drawable.islas);
                            nombreImagen = "islas";
                            break;
                        case PRADO:
                            imagen.setImageResource(R.drawable.prado);
                            nombreImagen = "prado";
                            break;
                        case VALLE:
                            imagen.setImageResource(R.drawable.valle);
                            nombreImagen = "valle";
                            break;
                        case PUEBLO:
                            imagen.setImageResource(R.drawable.pueblo);
                            nombreImagen = "pueblo";
                            break;
                        case CIUDAD:
                            imagen.setImageResource(R.drawable.catedral_sevilla);
                            nombreImagen = "catedral_sevilla";
                        default:

                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Acciones cuando no se selecciona nada (opcional)
                }
            });
            String[] tiposLugarStrings = new String[tiposLugar.length];
            for (int i = 0; i < tiposLugar.length; i++) {
                tiposLugarStrings[i] = tiposLugar[i].toString();
            }

            String tip = lugar.getTipoLugar().toString();
            for (int i = 0; i < tiposLugarStrings.length; i++) {
                if (tiposLugarStrings[i].equals(tip)) {
                    spinner.setSelection(i);
                    break;
                }
            }
            camera.setOnClickListener(view -> {
                Log.d("CAMARA", "pasa por aqui ");
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            });
            botonAceptar.setOnClickListener(vista -> {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fecha2 = LocalDate.parse(fecha.getText().toString(), formatter);
                    Lugar lugar = new Lugar(nombre.getText().toString(), direccion.getText().toString(),
                            new GeoPunto(Double.valueOf(longitud.getText().toString()), Double.valueOf(latitud.getText().toString())),
                            nombreImagen, url.getText().toString(), comentario.getText().toString(),
                            fecha2, (double) valoracion.getRating(),
                            TipoLugar.valueOf(spinner.getSelectedItem().toString()));
                    listaLugares = ((Aplicacion) requireActivity().getApplication()).listaLugares;
                    listaLugares.editarLugar(lugar);
                    Toast.makeText(getContext(), "Se edito correctamente", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Se produjo un error", Toast.LENGTH_SHORT).show();
                }
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentPrincipal);
                navController.navigate(R.id.vistaPrincipal);
            });

            return binding.getRoot();
        }
        @Override
        public void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                imagen.setImageBitmap(thumbnail);
            }
        }

}