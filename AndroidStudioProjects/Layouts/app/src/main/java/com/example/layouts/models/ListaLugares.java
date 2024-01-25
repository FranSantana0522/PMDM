package com.example.layouts.models;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.layouts.models.repository.ListaLugaresRepository;
import com.example.layouts.models.repository.impl.ListaLugaresRepositoryImpl;

import java.util.ArrayList;

public class ListaLugares {
    private ArrayList<Lugar> listaLugares = new ArrayList<>();

    private Context context;

    private ListaLugaresRepository listaLugaresRepository;

    public ListaLugares() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListaLugares(Context context) {
        this.context = context;
        this.listaLugaresRepository=new ListaLugaresRepositoryImpl(context);
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

    public void borrarTodo(){
        listaLugaresRepository.borrarTodo();
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
