package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        // Insertar información
        insertarInformacion(dbHelper);

        // Leer información
        leerInformacion(dbHelper);

        // Borrar información
        borrarInformacion(dbHelper);

        // Actualizar base de datos
        actualizarBD(dbHelper);
    }

    public void insertarInformacion(FeedReaderDbHelper dbHelper){
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define valores de ejemplo (sustituye estos con tus valores reales)
        String TITULO = "Ejemplo de título";
        String SUBTITULO = "Ejemplo de subtítulo";

        // Crea un nuevo mapa de valores, donde los nombres de las columnas son las claves
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, TITULO);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, SUBTITULO);

        // Inserta la nueva fila, devolviendo el valor de la clave principal de la nueva fila
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        // Agrega un mensaje de registro para ver el resultado
        Log.d("INSERT_OPERATION", "Nueva fila insertada con ID: " + newRowId);
    }

    public void leerInformacion(FeedReaderDbHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Mi Título" };
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";
        //Query
        Log.d("QUERY", "Consulta SQL: " + db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        ));
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        // Agrega un mensaje de registro para ver los resultados
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            Log.d("READ_OPERATION", "ID: " + itemId);
        }
        cursor.close();
    }

    public void borrarInformacion(FeedReaderDbHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define la parte 'where' de la consulta.
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";

        // Especifica los argumentos en orden de marcadores de posición.
        String[] selectionArgs = { "Mi Título" };

        // Emitir la instrucción SQL.
        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        // Agrega un mensaje de registro para ver los resultados
        Log.d("DELETE_OPERATION", "Filas eliminadas: " + deletedRows);
    }

    public void actualizarBD(FeedReaderDbHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Nuevo valor para una columna
        String title = "Mi Nuevo Título";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);

        // Qué fila actualizar, basado en el título
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "Mi Viejo Título" };

        int count = db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, selection, selectionArgs);
        // Agrega un mensaje de registro para ver los resultados
        Log.d("UPDATE_OPERATION", "Filas actualizadas: " + count);
    }
}