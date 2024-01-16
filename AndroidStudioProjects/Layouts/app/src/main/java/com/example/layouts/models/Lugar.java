package com.example.layouts.models;

public class Lugar {
    private String nombre;
    private String direccion;
    private GeoPunto infoLugar;

    public Lugar() {
    }

    public Lugar(String nombre, String direccion, GeoPunto infoLugar) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.infoLugar = infoLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public GeoPunto getInfoLugar() {
        return infoLugar;
    }

    public void setInfoLugar(GeoPunto infoLugar) {
        this.infoLugar = infoLugar;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", infoLugar=" + infoLugar +
                '}';
    }
}
