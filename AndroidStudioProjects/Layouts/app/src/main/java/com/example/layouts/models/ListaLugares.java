package com.example.layouts.models;

import android.content.Context;

import com.example.layouts.models.repository.ListaLugaresRepository;
import com.example.layouts.models.repository.impl.ListaLugaresRepositoryImpl;

import java.util.ArrayList;

public class ListaLugares {
    private ArrayList<Lugar> listaLugares = new ArrayList<>();

    private Context context;

    private ListaLugaresRepository listaLugaresRepository=new ListaLugaresRepositoryImpl(context);

    public ListaLugares() {
    }

    public ListaLugares(Context context) {
        this.context = context;
    }

    public ListaLugares ObtenerListaLugares(){
        return listaLugaresRepository.obtenerListaDeLugares();
    }

    public void añadirLugares(Lugar lugar){
        listaLugaresRepository.añadirLugar(lugar);
    }

    public void editarLugar(Lugar lugar){
        listaLugaresRepository.editarLugar(lugar);
    }

    public void borrarLugar(Lugar lugar){
        listaLugaresRepository.borrarLugar(lugar);
    }

    public ListaLugares(ArrayList<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
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
