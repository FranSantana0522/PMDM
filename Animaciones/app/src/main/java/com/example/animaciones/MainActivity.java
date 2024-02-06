package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    boolean continuar = true;
    float velocidad = 1.5f;
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    DinamicaView dinamica;
    float s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica = new DinamicaView(this);
        dinamica = new DinamicaView(this);
        dinamica.setContinuar(continuar);
        dinamica.setVelocidad(velocidad);
        dinamica.setDt(dt);
        dinamica.setTiempo(tiempo);
        dinamica.setS(s);
        setContentView(dinamica);
        s = getResources().getDisplayMetrics().density;
        hilo = new Thread(dinamica);
        hilo.start();
    }

    //detenemos el hilo si pausa
    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    //reiniciamos el hilo si resume
    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        if (!hilo.isAlive()) {
            hilo = new Thread(dinamica);
            hilo.start();
        }

    }
}