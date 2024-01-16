package com.example.layouts.models;

import java.util.ArrayList;

public class ListaLugares {
    private ArrayList<Lugar> listaLugares = new ArrayList<>();

    public ListaLugares() {
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
