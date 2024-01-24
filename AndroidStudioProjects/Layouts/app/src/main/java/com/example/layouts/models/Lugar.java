package com.example.layouts.models;

import java.time.LocalDate;

public class Lugar {

    private Integer id=0;
    private String nombre;
    private String direccion;
    private GeoPunto infoLugar;
    private String imagen;
    private String url;
    private String comentario;
    private LocalDate fecha;
    private Double valoracion;
    private TipoLugar tipoLugar;

    public Lugar() {
    }

    public Lugar(String nombre, String direccion, GeoPunto infoLugar, String imagen, String url, String comentario, LocalDate fecha, Double valoracion, TipoLugar tipoLugar) {
        this.id++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.infoLugar = infoLugar;
        this.imagen = imagen;
        this.url = url;
        this.comentario = comentario;
        this.fecha = fecha;
        this.valoracion = valoracion;
        this.tipoLugar = tipoLugar;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public TipoLugar getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(TipoLugar tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", infoLugar=" + infoLugar +
                ", imagen='" + imagen + '\'' +
                ", url='" + url + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                ", valoracion=" + valoracion +
                ", tipoLugar=" + tipoLugar +
                '}';
    }
}
