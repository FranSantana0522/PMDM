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
    float velocidadX = 1.5f;
    float velocidadY = 1.5f;
    int dt = 10;
    int tiempo = 0;
    TextView btn1X;
    TextView btn2X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        burbuja1 = findViewById(R.id.button1);
        burbuja2 = findViewById(R.id.button2);
        puntuacion = findViewById(R.id.texto);
        btn1X=findViewById(R.id.btn1X);
        btn2X=findViewById(R.id.btn2X);

//        burbuja1.setVisibility(View.INVISIBLE);
//        burbuja2.setVisibility(View.INVISIBLE);

        burbuja1.setX(100);
        burbuja1.setY(200);
        burbuja2.setX(500);
        burbuja2.setY(300);

        dinamica1 = new DinamicaView(this, burbuja1,btn1X);
        dinamica2 = new DinamicaView(this, burbuja2,btn2X);

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
        TextView texto;

        public DinamicaView(Context context, Button burbuja,TextView texto) {
            this.burbuja = burbuja;
            this.texto=texto;
            x = (int) burbuja.getX();
            y = (int) burbuja.getY();
            xmax = getResources().getDisplayMetrics().widthPixels;
            ymax = getResources().getDisplayMetrics().heightPixels;
        }

        @Override
        public void run() {
            while (continuar) {
                tiempo = tiempo + dt;
                y = y + (int) (velocidadY * dt);
                x = x + (int) (velocidadX * dt);
                int tex=x;
                texto.setText(tex);
//                if (y > ymax || y < 0 || x > xmax || x < 0) {
//                    velocidad = -velocidad;
//                }
                if(y>ymax){
                    velocidadY=-velocidadY;
                }
                if(y<0){
                    velocidadY=-velocidadY;
                }
                if(x>xmax){
                    velocidadX=-velocidadX;
                }
                if(x<0){
                    velocidadX=-velocidadX;
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
