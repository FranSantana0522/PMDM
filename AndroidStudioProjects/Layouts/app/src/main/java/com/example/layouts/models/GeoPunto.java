package com.example.layouts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoPunto implements Parcelable {
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

    protected GeoPunto(Parcel in) {
        longitud = in.readDouble();
        latitud = in.readDouble();
    }

    public static final Creator<GeoPunto> CREATOR = new Creator<GeoPunto>() {
        @Override
        public GeoPunto createFromParcel(Parcel in) {
            return new GeoPunto(in);
        }

        @Override
        public GeoPunto[] newArray(int size) {
            return new GeoPunto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(longitud);
        dest.writeDouble(latitud);
    }

    @Override
    public String toString() {
        return "GeoPunto{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }
}
