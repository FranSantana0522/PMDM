package com.example.mislugares.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugares.Aplicacion;
import com.example.mislugares.MainActivity;
import com.example.mislugares.R;
import com.example.mislugares.databinding.VistaPrincipalBinding;
import com.example.mislugares.models.GeoPunto;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.LugaresAdapter;
import com.example.mislugares.models.TipoLugar;

import java.time.LocalDate;

public class vistaPrincipal extends Fragment {

    private VistaPrincipalBinding binding;
    private ListaLugares listaLugares;
    private RecyclerView recyclerView;
    private LugaresAdapter lugaresAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = VistaPrincipalBinding.inflate(inflater, container, false);
        try {
            listaLugares=((Aplicacion)requireActivity().getApplication()).listaLugares;
            listaLugares = listaLugares.ObtenerListaLugares();
            recyclerView = binding.getRoot().findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            lugaresAdapter = new LugaresAdapter(getContext(), listaLugares.getListaLugares());
            recyclerView.setAdapter(lugaresAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        lugaresAdapter.setOnItemClickListener(new LugaresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Lugar lugar) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lugar", lugar);
                NavController navController = Navigation.findNavController(getActivity(),R.id.fragmentPrincipal);
                navController.navigate(R.id.detalleLugar, bundle);
            }
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     /*   binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(vistaPrincipal.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });  */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}