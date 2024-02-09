package com.example.killbug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean continuar = true;
    float velocidad = 1.5f;
    float aceleracion=0.5f;
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    DinamicaView dinamica;
    float s;
    int cantidadInsectos = 5;
    ArrayList<DinamicaView> insectos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = getResources().getDisplayMetrics().density;
        FrameLayout container = findViewById(R.id.container);

        for (int i = 0; i < cantidadInsectos; i++) {
            int posX = (int) (Math.random() * container.getWidth());
            int posY = (int) (Math.random() * container.getHeight());
            DinamicaView dinamica = new DinamicaView(this, posX, posY);
            insectos.add(dinamica);

            container.addView(dinamica);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        if (hilo == null || !hilo.isAlive()) {
            for (DinamicaView dinamicaView : insectos) {
                if (dinamicaView != null) {
                    hilo = new Thread(dinamicaView);
                    hilo.start();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    class DinamicaView extends View implements Runnable{
        int x,y,ymax,xmax;
        Paint paintFondo,paint;
        Drawable imagen;

        public DinamicaView(Context context, int initialX, int initialY) {
            super(context);
            x = initialX;
            y = initialY;
            paintFondo = new Paint();
            paint = new Paint();
            paintFondo.setColor(Color.WHITE);
            paint.setColor(Color.BLACK);
            imagen = ContextCompat.getDrawable(context, R.drawable.bicho);
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
            super.onSizeChanged(w, h, oldw, oldh);
            ymax=h;
            xmax=w;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            int anchoImagen = imagen.getIntrinsicWidth();
            int altoImagen = imagen.getIntrinsicHeight();

            int nuevoAncho = anchoImagen / 4;
            int nuevoAlto = altoImagen / 4;

            int xImagen = x - nuevoAncho / 2;
            int yImagen = y - nuevoAlto / 2;

            imagen.setBounds(xImagen, yImagen, xImagen + nuevoAncho, yImagen + nuevoAlto);
            imagen.draw(canvas);
        }

    }
}