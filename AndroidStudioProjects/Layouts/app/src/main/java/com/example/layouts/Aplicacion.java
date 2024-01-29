package com.example.layouts;

import android.app.Application;

import com.example.layouts.models.ListaLugares;

public class Aplicacion extends Application {
    public ListaLugares listaLugares;
    @Override
    public void onCreate() {
        super.onCreate();
        listaLugares=ListaLugares.getInstance(this);
    }
}
