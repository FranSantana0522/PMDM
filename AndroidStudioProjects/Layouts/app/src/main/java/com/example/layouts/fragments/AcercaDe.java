package com.example.layouts.fragments;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.R;

public class AcercaDe extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de);
        Button btnVolver=findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(view->{
            finish();
        });
    }
}
