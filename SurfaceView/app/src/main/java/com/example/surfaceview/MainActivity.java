package com.example.surfaceview;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usar MiSurfaceView en lugar de inflar un layout desde un archivo XML
        GameSurfaceView miSurfaceView = new GameSurfaceView(this);
        setContentView(miSurfaceView);
    }
}
