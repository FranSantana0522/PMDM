package com.example.ex2ev2pmdmfranciscosantanamontes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Thread hilo;
    boolean continuar = true;
    Button burbuja1;
    Button burbuja2;
    TextView puntuacion;
    int puntuacionSuma = 0;
    DinamicaView dinamica1;
    DinamicaView dinamica2;
    float velocidad = 1.5f;
    int dt = 10;
    int tiempo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        burbuja1 = findViewById(R.id.button1);
        burbuja2 = findViewById(R.id.button2);
        puntuacion = findViewById(R.id.texto);

        burbuja1.setVisibility(View.INVISIBLE);
        burbuja2.setVisibility(View.INVISIBLE);

        burbuja1.setX(100);
        burbuja1.setY(200);
        burbuja2.setX(500);
        burbuja2.setY(300);

        dinamica1 = new DinamicaView(this, burbuja1);
        dinamica2 = new DinamicaView(this, burbuja2);

        dinamica1.start();
        dinamica2.start();

        burbuja1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarPuntuacion(burbuja1);
            }
        });

        burbuja2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarPuntuacion(burbuja2);
            }
        });
    }

    public void sumarPuntuacion(Button burbuja) {
        puntuacionSuma++;
        puntuacion.setText(String.valueOf(puntuacionSuma));
        burbuja.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                burbuja.setVisibility(View.VISIBLE);
            }
        }, 5000);

    }
    @Override
    protected void onPause() {
        super.onPause();
        continuar = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        continuar = true;
    }
    class DinamicaView extends Thread {
        Button burbuja;
        int x, y, ymax, xmax;

        public DinamicaView(Context context, Button burbuja) {
            this.burbuja = burbuja;
            x = (int) burbuja.getX();
            y = (int) burbuja.getY();
            xmax = getResources().getDisplayMetrics().widthPixels;
            ymax = getResources().getDisplayMetrics().heightPixels;
        }

        @Override
        public void run() {
            while (continuar) {
                tiempo = tiempo + dt;
                y = y + (int) (velocidad * dt);
                x = x + (int) (velocidad * dt);

                if (y > ymax || y < 0 || x > xmax || x < 0) {
                    velocidad = -velocidad;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        burbuja.setX(x);
                        burbuja.setY(y);
                    }
                });

                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
