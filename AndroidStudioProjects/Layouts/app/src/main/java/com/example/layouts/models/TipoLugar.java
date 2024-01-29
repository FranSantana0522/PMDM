package com.example.layouts.models;

import android.os.Parcel;
import android.os.Parcelable;

public enum TipoLugar implements Parcelable {
    MONTANIA,
    RIO,
    PLAYA,
    ISLA,
    VALLE,
    PRADO,
    LAGO,
    CIUDAD,
    PUEBLO;
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TipoLugar> CREATOR = new Creator<TipoLugar>() {
        @Override
        public TipoLugar createFromParcel(Parcel in) {
            return TipoLugar.values()[in.readInt()];
        }

        @Override
        public TipoLugar[] newArray(int size) {
            return new TipoLugar[size];
        }
    };
}
