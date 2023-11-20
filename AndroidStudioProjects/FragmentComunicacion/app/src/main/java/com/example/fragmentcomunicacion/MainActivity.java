package com.example.fragmentcomunicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnControlesFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container,new ControlesFragment()).commit();
    }

    @Override
    public void botonColorCliked(String color) {
        Toast.makeText(this,"Estoy en el main activity, color recibido "+color,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void botonTextoClicked(String texto) {
        Toast.makeText(this,"Estoy en el main activity, texto recibido "+texto,Toast.LENGTH_SHORT).show();

    }
}