package com.example.mislugares.models;

import android.annotation.SuppressLint;
import android.content.Context;
import com.example.mislugares.models.repository.ListaLugaresRepository;
import com.example.mislugares.models.repository.impl.ListaLugaresRepositoryImpl;

import java.util.ArrayList;

public class ListaLugares {
    private static ListaLugares instance;
    private ArrayList<Lugar> listaLugares = new ArrayList<>();
    private Context context;
    private ListaLugaresRepository listaLugaresRepository;

    public ListaLugares() {
    }

    @SuppressLint("NewApi")
    private ListaLugares(Context context) {
        this.context = context;
        this.listaLugaresRepository = new ListaLugaresRepositoryImpl(context);
    }

    public static ListaLugares getInstance(Context context) {
        if (instance == null) {
            instance = new ListaLugares(context);
        }
        return instance;
    }

    public ListaLugares ObtenerListaLugares() {
        return listaLugaresRepository.obtenerListaDeLugares();
    }

    public void añadirLugares(Lugar lugar) {
        listaLugaresRepository.añadirLugar(lugar);
    }

    public void editarLugar(Lugar lugar) {
        listaLugaresRepository.editarLugar(lugar);
    }

    public void borrarLugar(Lugar lugar) {
        listaLugaresRepository.borrarLugar(lugar);
    }

    public void borrarTodo() {
        listaLugaresRepository.borrarTodo();
    }

    public ArrayList<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(ArrayList<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    @Override
    public String toString() {
        return "ListaLugares{" +
                "listaLugares=" + listaLugares +
                '}';
    }
}
