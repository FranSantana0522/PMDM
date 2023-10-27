package com.example.api24_android70java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private TextView texto2;
    private Button button;
    private ImageButton imagebutton;
    protected Integer cont=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG CICLO VIDA", "método·onCreate");

        texto = (TextView) findViewById(R.id.texto);
        texto2 = (TextView) findViewById(R.id.texto2);
        button = (Button) findViewById(R.id.button);
        imagebutton = (ImageButton) findViewById(R.id.imageButton);

        texto.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {//Toast “Se ha hecho click sobre un TextView”

                Toast.makeText(MainActivity.this, "Se ha hecho click "+ texto.getText(), Toast.LENGTH_SHORT).show();
                button.setText("Reset");
            }

        });
        texto2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {//Toast “Se ha hecho click sobre un TextView”

                Toast.makeText(MainActivity.this, "Se ha hecho click " + texto2.getText(), Toast.LENGTH_SHORT).show();
                texto2.setText("Hola Mundo");
            }

        });
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {//Toast “Se ha hecho click sobre un TextView”

                Toast.makeText(MainActivity.this, "Se ha hecho click " + button.getText(), Toast.LENGTH_SHORT).show();
                cont=0;
                texto2.setText("Se ha hecho click "+ cont +" veces");
            }

        });
        imagebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {//Toast “Se ha hecho click sobre un TextView”

                Toast.makeText(MainActivity.this, "Se ha hecho click en +1", Toast.LENGTH_SHORT).show();
                texto2.setText("Se ha hecho click "+ cont++ +" veces");
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG CICLO VIDA", "método·onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG CICLO VIDA", "método·onResume");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAG CICLO VIDA", "método·onRestart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG CICLO VIDA", "método·onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG CICLO VIDA", "método·onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG CICLO VIDA", "método·onDestroy");
    }


}