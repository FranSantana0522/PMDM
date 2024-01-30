package com.example.mislugares.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
    private TextView url;
    private EditText comentario;
    private EditText fecha;
    private RatingBar valoracion;
    private Lugar lugar;
    private Button borrar;
    private ListaLugares listaLugares;
    private OnLugarChangeListener mListener;

    public interface OnLugarChangeListener {
        void onLugarChanged(Lugar lugar);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = DetalleLugarBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
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
                if (nombreImagen.equalsIgnoreCase(nombreRecurso)) {
                    imagen.setImageResource(imagenResource);
                }
            }

        }
        pasarLugar();
        url.setOnClickListener(view->{
            String url = lugar.getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        borrar.setOnClickListener(view->{
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle(R.string.borrar);
            builder.setMessage(R.string.confirmarBorrar);
            builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listaLugares = ((Aplicacion) requireActivity().getApplication()).listaLugares;
                    listaLugares.borrarLugar(lugar);
                    dialog.dismiss();
                    NavController navController = Navigation.findNavController(getActivity(),R.id.fragmentPrincipal);
                    navController.navigate(R.id.vistaPrincipal);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
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
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnLugarChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
    private void pasarLugar() {
        if (mListener != null) {
            mListener.onLugarChanged(lugar);
        }
    }

}