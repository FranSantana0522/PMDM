package com.example.imagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int numero=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagen = findViewById(R.id.imagen);
        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnAdelante = findViewById(R.id.btnAdelante);

        int listaImagenes []=new int[3];
        listaImagenes[0]=R.drawable.a4206978785_10;
        listaImagenes[1]=R.drawable.surprise;
        listaImagenes[2]=R.drawable.surpriseold;

        imagen.setImageResource(listaImagenes[0]);

        btnAdelante.setOnClickListener(view->{
            numero++;
            if (numero>2) {
                numero=0;
                imagen.setImageResource(listaImagenes[numero]);
            }else {
                imagen.setImageResource(listaImagenes[numero]);
            }
        });

        btnAtras.setOnClickListener(view->{
            numero--;
            if(numero<0){
                numero=2;
                imagen.setImageResource(listaImagenes[numero]);
            }else {
                imagen.setImageResource(listaImagenes[numero]);
            }
        });
    }
}