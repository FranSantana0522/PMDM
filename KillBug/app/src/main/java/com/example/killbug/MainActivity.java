package com.example.killbug;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView[] imagenBicho = new ImageView[5];
    private Drawable[] drawableBicho = new Drawable[5];
    private Drawable[] aplastaoBicho = new Drawable[5];
    private boolean[] aplastao = new boolean[5];
    private MediaPlayer sonidoAplastao;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonidoAplastao = MediaPlayer.create(this, R.raw.golpe);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                bichos();
                handler.postDelayed(this, 2147483647); // Máximo valor de tiempo
                // Cambia la velocidad de generación de insectos

            }
        };
        handler.postDelayed(runnable, 0);

        int[] ids = {R.id.bug1, R.id.bug2, R.id.bug3, R.id.bug4, R.id.bug5};
        for (int i = 0; i < 5; i++) {
            imagenBicho[i] = findViewById(ids[i]);
        }

        // Asignación de drawables
        for (int i = 0; i < 5; i++) {
            drawableBicho[i] = getResources().getDrawable(R.drawable.bicho);
            aplastaoBicho[i] = getResources().getDrawable(R.drawable.aplastao);
        }

        // Configuración de clics
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            imagenBicho[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!aplastao[finalI]) {
                        sonidoAplastao.start();
                        imagenBicho[finalI].setImageDrawable(aplastaoBicho[finalI]);
                        aplastao[finalI] = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Después de 1 segundo, cambiar la imagen a la normal
                                imagenBicho[finalI].setImageDrawable(drawableBicho[finalI]);
                                aplastao[finalI] = false;
                                ((ViewGroup) imagenBicho[finalI].getParent()).removeView(imagenBicho[finalI]);
                            }
                        }, 1000); // Mostrar la imagen aplastada durante 1 segundo
                    }
                }
            });
        }
    }

    private void bichos() {
        Random random = new Random();

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        for (int i = 0; i < 5; i++) {
            final ImageView bugImage = imagenBicho[i];

            // Inicializar la posición del insecto
            float startX = random.nextInt(screenWidth);
            float startY = random.nextInt(screenHeight);

            // Ajustar la posición del insecto para que esté dentro de los límites de la pantalla
            if (startX > screenWidth - bugImage.getWidth()) {
                startX = screenWidth - bugImage.getWidth();
            }
            if (startY > screenHeight - bugImage.getHeight()) {
                startY = screenHeight - bugImage.getHeight();
            }

            bugImage.setX(startX);
            bugImage.setY(startY);

            // Calcular la velocidad del insecto (componentes dx y dy)
            float targetX = random.nextInt(screenWidth);
            float targetY = random.nextInt(screenHeight);
            float dx = targetX - startX;
            float dy = targetY - startY;

            // Calcular la magnitud total de la velocidad
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            // Normalizar la velocidad para mantener la misma dirección, pero con magnitud de 1
            dx /= distance;
            dy /= distance;

            // Definir la velocidad de movimiento del insecto
            final float speed = 5.0f;

            // Variables auxiliares para las componentes dx y dy
            float tempDx = dx;
            float tempDy = dy;

            // Actualizar la posición del insecto de forma continua
            final float finalSpeed = speed;
            handler.post(new Runnable() {
                float finalDx = tempDx; // Usamos una variable auxiliar para finalDx
                float finalDy = tempDy; // Usamos una variable auxiliar para finalDy

                @Override
                public void run() {
                    // Mover el insecto hacia el objetivo con la velocidad y dirección calculadas
                    float x = bugImage.getX() + finalDx * finalSpeed;
                    float y = bugImage.getY() + finalDy * finalSpeed;

                    // Ajustar la dirección si el insecto choca contra los bordes de la pantalla
                    if (x <= 0 || x >= screenWidth - bugImage.getWidth()) {
                        finalDx *= -1; // Cambiar la dirección horizontal
                    }
                    if (y <= 0 || y >= screenHeight - bugImage.getHeight()) {
                        finalDy *= -1; // Cambiar la dirección vertical
                    }

                    // Actualizar la posición del insecto
                    bugImage.setX(x);
                    bugImage.setY(y);

                    // Programar la próxima actualización de posición
                    handler.postDelayed(this, 10); // Actualizar cada 10 milisegundos
                }
            });
        }
    }

}













































/*
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
            int posX = (int) (Math.random() * (container.getWidth()));
            int posY = (int) (Math.random() * (container.getHeight()));
            DinamicaView dinamica = new DinamicaView(this, posX, posY);
            insectos.add(dinamica);
            container.addView(dinamica);

            Thread hilo = new Thread(dinamica);
            hilo.start();
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

            int anchoImagen = imagen.getIntrinsicWidth();
            int altoImagen = imagen.getIntrinsicHeight();

            int nuevoAncho = anchoImagen / 4;
            int nuevoAlto = altoImagen / 4;

            int xImagen = x - nuevoAncho / 2;
            int yImagen = y - nuevoAlto / 2;

            imagen.setBounds(xImagen, yImagen, xImagen + nuevoAncho, yImagen + nuevoAlto);
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
            imagen.draw(canvas);
        }

    }
}

 */