package com.example.juegodinosaurio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=bQjfmB3lRoA
    // https://es.pixilart.com/draw/edit-your-8-bit-c8fb7d520785af1 dibujo del dinosaurio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameSurfaceView game= new GameSurfaceView(this);
        setContentView(game);

    }
}