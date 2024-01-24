package com.example.ex1ev2franciscojaviersantanamontes;

public class Imagen {
    private int idImagen;
    private int idSonido;

    public Imagen(int idImagen, int idSonido) {
        this.idImagen = idImagen;
        this.idSonido = idSonido;
    }

    public int getIdImagen() {
        return idImagen;
    }
    public int getIdSonido() {
        return idSonido;
    }
}
