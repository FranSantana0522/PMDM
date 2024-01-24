package com.example.layouts.models.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.layouts.R;
import com.example.layouts.models.GeoPunto;
import com.example.layouts.models.ListaLugares;
import com.example.layouts.models.Lugar;
import com.example.layouts.models.TipoLugar;
import com.example.layouts.models.repository.ListaLugaresRepository;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaLugaresRepositoryImpl implements ListaLugaresRepository {

    private BDSqlite bdSqlite;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListaLugaresRepositoryImpl(Context context) {
        this.context = context;
        bdSqlite = new BDSqlite(context);
    }

    @Override
    public ListaLugares obtenerListaDeLugares() {
        SQLiteDatabase db = bdSqlite.getReadableDatabase();
        String[] projection = null;
        ListaLugares listaLugaresObj = new ListaLugares();
        Cursor cursor = db.query(
                "lugar",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            double longitud = cursor.getDouble(cursor.getColumnIndexOrThrow("longitud"));
            double latitud = cursor.getDouble(cursor.getColumnIndexOrThrow("latitud"));
            String imagen = cursor.getString(cursor.getColumnIndexOrThrow("imagen"));
            String url = cursor.getString(cursor.getColumnIndexOrThrow("url"));
            String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
            String fechaStr = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
            LocalDate fecha = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                fecha = LocalDate.parse(fechaStr);
            }
            double valoracion = cursor.getDouble(cursor.getColumnIndexOrThrow("valoracion"));
            String tipoLugarStr = cursor.getString(cursor.getColumnIndexOrThrow("tipolugar"));
            TipoLugar tipoLugar = TipoLugar.valueOf(tipoLugarStr);

            Lugar lugar = new Lugar();
            lugar.setId(id);
            lugar.setNombre(nombre);
            lugar.setDireccion(direccion);
            lugar.setInfoLugar(new GeoPunto(latitud, longitud));
            lugar.setImagen(imagen);
            lugar.setUrl(url);
            lugar.setComentario(comentario);
            lugar.setFecha(fecha);
            lugar.setValoracion(valoracion);
            lugar.setTipoLugar(tipoLugar);

            listaLugaresObj.getListaLugares().add(lugar);
        }

        cursor.close();
        return listaLugaresObj;
    }


    @Override
    public void añadirLugar(Lugar lugar) {
        SQLiteDatabase db = bdSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", lugar.getNombre());
        values.put("direccion", lugar.getDireccion());
        values.put("longitud", lugar.getInfoLugar().getLongitud());
        values.put("latitud", lugar.getInfoLugar().getLatitud());
        values.put("imagen", lugar.getImagen());
        values.put("url", lugar.getUrl());
        values.put("comentario", lugar.getComentario());
        values.put("fecha", lugar.getFecha().toString());
        values.put("valoracion", lugar.getValoracion());
        values.put("tipolugar", lugar.getTipoLugar().toString());

        long newRowId = db.insert("lugar", null, values);
        if (newRowId != -1) {
            Toast.makeText(context, "Se ha insertado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No se ha podido insertar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void borrarLugar(Lugar lugar) {
        SQLiteDatabase db = bdSqlite.getWritableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(lugar.getId())};

        int deletedRows = db.delete("lugar", selection, selectionArgs);

        if (deletedRows > 0) {
            Toast.makeText(context, "Se ha borrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No se ha podido borrar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void editarLugar(Lugar lugar) {
        SQLiteDatabase db = bdSqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", lugar.getNombre());
        values.put("direccion", lugar.getDireccion());
        values.put("longitud", lugar.getInfoLugar().getLongitud());
        values.put("latitud", lugar.getInfoLugar().getLatitud());
        values.put("imagen", lugar.getImagen());
        values.put("url", lugar.getUrl());
        values.put("comentario", lugar.getComentario());
        values.put("fecha", lugar.getFecha().toString());
        values.put("valoracion", lugar.getValoracion());
        values.put("tipolugar", lugar.getTipoLugar().toString());

        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(lugar.getId())};

        int updatedRows = db.update("lugar", values, selection, selectionArgs);

        if (updatedRows > 0) {
            Toast.makeText(context, "Se ha editado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No se ha podido editar", Toast.LENGTH_SHORT).show();
        }
    }
}
