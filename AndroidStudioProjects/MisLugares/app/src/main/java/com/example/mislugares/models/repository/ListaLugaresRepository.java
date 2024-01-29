package com.example.mislugares.models.repository;

import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;

public interface ListaLugaresRepository {
    ListaLugares obtenerListaDeLugares();
    void añadirLugar(Lugar lugar);
    void borrarLugar(Lugar lugar);
    void editarLugar(Lugar lugar);
    void borrarTodo();
}
