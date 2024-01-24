package com.example.layouts.models.repository.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDSqlite extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Lugares.db";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE lugar(id integer primary key, nombre text, direccion text," +
            "longitud real, latitud real, imagen text, url text, comentario text, fecha text, valoracion real, tipolugar text )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS lugar";
    public BDSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
