package com.example.mislugares.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mislugares.R;
import com.example.mislugares.databinding.AcercaDeBinding;
import com.example.mislugares.databinding.VistaPrincipalBinding;

public class AcercaDe extends Fragment {
    private AcercaDeBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AcercaDeBinding.inflate(inflater, container, false);
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
