package com.example.animacionacelerada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean continuar = true;
    float velocidad = 1.5f;

    float aceleracion=0.1f;
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    DinamicaView dinamica;
    float s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica = new DinamicaView(this);
        setContentView(dinamica);
        s = getResources().getDisplayMetrics().density;
        hilo = new Thread(dinamica);
        hilo.start();
    }

    //detenemos el hilo si pausa
    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    //reiniciamos el hilo si resume
    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        if (!hilo.isAlive()) {
            hilo = new Thread(dinamica);
            hilo.start();
        }

    }
    class DinamicaView extends View implements Runnable{
        int x,y,ymax,xmax;
        Paint paintFondo,paintParticula,paint;
        public DinamicaView(Context context) {
            super(context);
            paintFondo=new Paint();
            paintParticula=new Paint();
            paint=new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
        }
        @Override
        public void run() {
            while(continuar){
                tiempo=tiempo+dt;
                y=y+(int)(velocidad+aceleracion*dt);
                x=x+(int)(velocidad*dt);
                if(y>ymax){
                    velocidad=-velocidad;
                    aceleracion=-aceleracion;
                }
                if(y<0){
                    velocidad=-velocidad;
                    aceleracion=-aceleracion;
                }
                if(x>xmax){
                    velocidad=-velocidad;
                }
                if(x<0){
                    velocidad=-velocidad;
                }

                postInvalidate();
                try{Thread.sleep(dt);}
                catch (InterruptedException e){

                }
            }
        }
        @Override
        protected void onSizeChanged(int w,int h,int oldw,int oldh){
            x=w/2;
            y=0;
            ymax=h;
            xmax=w;
        }
        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            paint.setTextSize(20 * s);
            canvas.drawCircle(x, y, 30 * s, paintParticula);
            canvas.drawText("y= " + y, 10 * s, 25 * s, paint);
            canvas.drawText("x= " + x, 10 * s, 50 * s, paint);
            canvas.drawText("tiempo= " + tiempo, 10 * s, 75 * s, paint);

        }
    }
}