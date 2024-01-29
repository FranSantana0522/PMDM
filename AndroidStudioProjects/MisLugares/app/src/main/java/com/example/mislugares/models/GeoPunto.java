package com.example.mislugares.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GeoPunto implements Serializable {
    private Double longitud;
    private Double latitud;

    public GeoPunto(Double longitud, Double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }


    @Override
    public String toString() {
        return "GeoPunto{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }
}
