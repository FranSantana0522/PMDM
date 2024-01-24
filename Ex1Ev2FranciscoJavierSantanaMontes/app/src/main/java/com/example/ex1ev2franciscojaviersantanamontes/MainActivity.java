package com.example.ex1ev2franciscojaviersantanamontes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Imagen> imagenList = new ArrayList<>();
        imagenList.add(new Imagen(R.drawable.im_buho,R.raw.buho));
        imagenList.add(new Imagen(R.drawable.im_colibri,R.raw.colibri));
        imagenList.add(new Imagen(R.drawable.im_cuervo,R.raw.cuervo));
        imagenList.add(new Imagen(R.drawable.im_kiwi,R.raw.kiwi));
        imagenList.add(new Imagen(R.drawable.im_loro,R.raw.loro));
        imagenList.add(new Imagen(R.drawable.im_pavo,R.raw.pavo));
        imagenList.add(new Imagen(R.drawable.im_pinguino,R.raw.pinguino));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ImagenAdapter imagenAdapter = new ImagenAdapter(this, imagenList);
        recyclerView.setAdapter(imagenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}