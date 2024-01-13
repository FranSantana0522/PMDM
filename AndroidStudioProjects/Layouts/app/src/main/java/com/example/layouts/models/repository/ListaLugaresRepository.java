package com.example.layouts.models.repository;

import com.example.layouts.models.ListaLugares;
import com.example.layouts.models.Lugar;

import java.util.ArrayList;

public interface ListaLugaresRepository {
    ArrayList<ListaLugares> obtenerListaDeLugares();
    void añadirLugar(Lugar lugar);
    void borrarLugar(Lugar lugar);
    void editarLugar(Lugar lugar);
}
