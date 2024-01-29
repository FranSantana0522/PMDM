package com.example.layouts.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Lugar implements Parcelable {

    private Integer id = 0;
    private String nombre;
    private String direccion;
    private GeoPunto infoLugar;
    private String imagen;
    private String url;
    private String comentario;
    private LocalDate fecha;
    private Double valoracion;
    private TipoLugar tipoLugar;

    // Constructor vacío
    public Lugar() {
    }

    // Constructor que acepta todos los campos
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Lugar(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        direccion = in.readString();
        // Deserializa el objeto GeoPunto
        infoLugar = in.readParcelable(GeoPunto.class.getClassLoader());
        imagen = in.readString();
        url = in.readString();
        comentario = in.readString();
        // Deserializa la fecha
        long fechaLong = in.readLong();
        fecha = LocalDate.ofEpochDay(fechaLong);
        valoracion = in.readDouble();
        // Deserializa el objeto TipoLugar
        tipoLugar = in.readParcelable(TipoLugar.class.getClassLoader());
    }

    public static final Creator<Lugar> CREATOR = new Creator<Lugar>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public Lugar createFromParcel(Parcel in) {
            return new Lugar(in);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(direccion);
        dest.writeParcelable(infoLugar, flags);
        dest.writeString(imagen);
        dest.writeString(url);
        dest.writeString(comentario);
        dest.writeLong(fecha.toEpochDay());
        dest.writeDouble(valoracion);
        dest.writeParcelable(tipoLugar, flags);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
