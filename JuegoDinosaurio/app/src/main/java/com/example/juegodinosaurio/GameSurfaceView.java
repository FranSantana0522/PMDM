package com.example.juegodinosaurio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import java.util.Random;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final int[][] laberinto;//Laberinto
    private final int numeroFilas;// Numero de filas del laberinto
    private final int numeroColumnas;
    private final int anchoBloque;
    private final int altoBloque;
    private Handler handler = new Handler();
    private int posX; // Posición X actual del personaje
    private int posY; // Posición Y actual del personaje
    private int puntos=0; // Puntuación del jugador
    private int maxPuntos=0;
    private int vidas=5;
    private boolean juegoTerminado = false;
    private boolean juegoPerdido = false;
    private boolean mostrarBoton = false;
    private Bitmap personajeArriba;
    private Bitmap personajeAbajo;
    private Bitmap personajeIzquierda;
    private Bitmap personajeDerecha;
    private Bitmap personajeBitmap;
    private Direccion direccionPersonaje = Direccion.DERECHA; // Inicialmente el personaje mira hacia la derecha
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerPuntoRecogido;
    private MediaPlayer mediaPlayerDaño;
    private MediaPlayer mediaPlayerPerder;
    private MediaPlayer mediaPlayerVictoria;
    private MediaPlayer mediaPlayerMusicaFondo;


    public GameSurfaceView(Context context) {
        this(context, null);
    }
    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Laberinto laberintoObj = new Laberinto();
        laberinto = laberintoObj.obtenerLaberinto();
        numeroFilas = laberintoObj.getNumeroFilas();
        numeroColumnas = laberintoObj.getNumeroColumnas();
        getMaxPuntos();
        anchoBloque = laberintoObj.getAnchoBloque();
        altoBloque = laberintoObj.getAltoBloque();
        encontrarPosicionInicial();
        personajeArriba = escalarBitmap(R.drawable.dinosauriobocaarriba, 40, 40);
        personajeAbajo = escalarBitmap(R.drawable.dinosauriobocaabajo, 40, 40);
        personajeIzquierda = escalarBitmap(R.drawable.dinosaurioladoizquierdo, 40, 40);
        personajeDerecha = escalarBitmap(R.drawable.dinosaurioladoderecho, 40, 40);
        personajeBitmap = personajeDerecha;
        mediaPlayer = MediaPlayer.create(context, R.raw.step_dino);
        mediaPlayerPuntoRecogido = MediaPlayer.create(context, R.raw.comer_puntos);
        mediaPlayerDaño = MediaPlayer.create(context, R.raw.danio);
        mediaPlayerPerder = MediaPlayer.create(context, R.raw.perder);
        mediaPlayerVictoria = MediaPlayer.create(context, R.raw.victoria);
        mediaPlayerMusicaFondo = MediaPlayer.create(context, R.raw.musica_fondo);
        mediaPlayerMusicaFondo.setLooping(true); // Repetir en bucle
        mediaPlayerMusicaFondo.start(); // Iniciar la reproducción
        getHolder().addCallback(this);
        iniciarCambiosTriangulos();
    }
    private Bitmap escalarBitmap(int resId, int nuevoAncho, int nuevoAlto) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        return Bitmap.createScaledBitmap(bitmap, nuevoAncho, nuevoAlto, false);
    }
    private void dibujarPersonaje(Canvas canvas) {
        switch (direccionPersonaje) {
            case ARRIBA:
                personajeBitmap = personajeArriba;
                break;
            case ABAJO:
                personajeBitmap = personajeAbajo;
                break;
            case IZQUIERDA:
                personajeBitmap = personajeIzquierda;
                break;
            case DERECHA:
                personajeBitmap = personajeDerecha;
                break;
        }
        if (personajeBitmap != null) {
            canvas.drawBitmap(personajeBitmap, posX * anchoBloque, posY * altoBloque, null);
        }
    }
    private void moverPersonaje(Direccion direccion) {
        switch (direccion) {
            case ARRIBA:
                moverArriba();
                direccionPersonaje=Direccion.ARRIBA;
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
                break;
            case ABAJO:
                moverAbajo();
                direccionPersonaje=Direccion.ABAJO;
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
                break;
            case IZQUIERDA:
                moverIzquierda();
                direccionPersonaje=Direccion.IZQUIERDA;
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
                break;
            case DERECHA:
                moverDerecha();
                direccionPersonaje=Direccion.DERECHA;
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
                break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!juegoTerminado && !juegoPerdido) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();
                Direccion direccion = obtenerDireccionTactil(x, y);
                if (direccion != null) {
                    moverPersonaje(direccion);
                    return true; // Se ha manejado el evento
                }
            }
        }else{
            if (event.getAction() == MotionEvent.ACTION_DOWN && mostrarBoton) {
                // Verificar si se ha tocado el área del botón
                float x = event.getX();
                float y = event.getY();
                if (x >= getWidth() / 2 - 300 && x <= getWidth() / 2 + 300 &&
                        y >= getHeight() - 200 && y <= getHeight() - 50) {
                    reiniciarJuego(); // Reiniciar el juego cuando se toca el botón
                }
            }
        }
        return super.onTouchEvent(event);
    }
    private Direccion obtenerDireccionTactil(float x, float y) {
        // Calcular la diferencia en coordenadas X y Y entre el evento táctil y la posición del personaje
        float diffX = x - (posX * anchoBloque);
        float diffY = y - (posY * altoBloque);

        // Comparar las diferencias en coordenadas X y Y para determinar la dirección
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX > 0) {
                return Direccion.DERECHA;
            } else {
                return Direccion.IZQUIERDA;
            }
        } else {
            if (diffY > 0) {
                return Direccion.ABAJO;
            } else {
                return Direccion.ARRIBA;
            }
        }
    }

    private void moverArriba() {
        if (posY > 0 && laberinto[posY - 1][posX] != 1) { // Verificar si la casilla de arriba no es un muro
            posY--; // Mover hacia arriba
            procesarCasilla(); // Procesar la casilla a la que se movió el personaje
        }
    }
    private void moverAbajo() {
        if (posY < numeroFilas - 1 && laberinto[posY + 1][posX] != 1) { // Verificar si la casilla de abajo no es un muro
            posY++; // Mover hacia abajo
            procesarCasilla(); // Procesar la casilla a la que se movió el personaje
        }
    }
    private void moverIzquierda() {
        if (posX > 0 && laberinto[posY][posX - 1] != 1) { // Verificar si la casilla de la izquierda no es un muro
            posX--; // Mover hacia la izquierda
            procesarCasilla(); // Procesar la casilla a la que se movió el personaje
        }
    }
    private void moverDerecha() {
        if (posX < numeroColumnas - 1 && laberinto[posY][posX + 1] != 1) { // Verificar si la casilla de la derecha no es un muro
            posX++; // Mover hacia la derecha
            procesarCasilla(); // Procesar la casilla a la que se movió el personaje
        }
    }
    private void procesarCasilla() {
        switch (laberinto[posY][posX]) {
            case 2: // Casilla con puntos
                puntos++; // Incrementar la puntuación
                laberinto[posY][posX] = 5; // Eliminar los puntos del laberinto
                if (mediaPlayerPuntoRecogido != null) {
                    mediaPlayerPuntoRecogido.start();
                }
                if(puntos==maxPuntos){
                    if (mediaPlayerVictoria != null) {
                        mediaPlayerVictoria.start();
                    }
                    juegoTerminado = true;
                    detenerJuego();
                }
                break;
            case 3: // Casilla con pincho
                // Restar vidas
                vidas--;
                if (mediaPlayerDaño != null) {
                    mediaPlayerDaño.start();
                }
                if(vidas==0){
                    if (mediaPlayerPerder != null) {
                        mediaPlayerPerder.start();
                    }
                    juegoPerdido=true;
                    detenerJuego();
                }
                break;
        }
        updateView(); // Actualizar la vista después del movimiento del personaje
    }
    private void encontrarPosicionInicial() {
        // Iterar sobre la matriz del laberinto para encontrar la posición inicial del personaje
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 0) {
                    // Establecer las coordenadas del personaje en la posición del primer 0 encontrado
                    posX = j;
                    posY = i;
                    return; // Salir del método una vez que se ha encontrado la posición inicial
                }
            }
        }
    }

    private void iniciarCambiosTriangulos() {
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 3) {
                    int tiempoAleatorio = new Random().nextInt(2000) + 1000; // Tiempo aleatorio entre 1000 y 3000 ms
                    handler.postDelayed(new CambiarTrianguloRunnable(i, j), tiempoAleatorio);
                }
            }
        }
    }

    private class CambiarTrianguloRunnable implements Runnable {
        private final int fila;
        private final int columna;

        public CambiarTrianguloRunnable(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void run() {
            if (laberinto[fila][columna] == 3) {
                laberinto[fila][columna] = 4; // Cambiar de 3 a 4
                updateView();
                handler.postDelayed(new ReaparecerTrianguloRunnable(fila, columna), 2000); // Esperar 2 segundos antes de reaparecer
            }
        }
    }

    private class ReaparecerTrianguloRunnable implements Runnable {
        private final int fila;
        private final int columna;

        public ReaparecerTrianguloRunnable(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void run() {
            if (laberinto[fila][columna] == 4) {
                laberinto[fila][columna] = 3; // Cambiar de 4 a 3
                updateView();
                handler.postDelayed(new CambiarTrianguloRunnable(fila, columna), 2000); // Repetir cada 2 segundos
            }
            if (laberinto[fila][columna] == 3 && (posX == columna && posY == fila)) {
                procesarCasilla(); // Si el personaje está en la casilla, procesarla
            }
        }
    }

    private void updateView() {
        SurfaceHolder holder = getHolder();
        if (holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                dibujarLaberinto(canvas);
                dibujarPersonaje(canvas);
                dibujarBoton(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        updateView();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        updateView();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        handler.removeCallbacksAndMessages(null);
    }

    private void dibujarLaberinto(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                switch (laberinto[i][j]) {
                    case 1:
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(j * anchoBloque, i * altoBloque,
                                (j + 1) * anchoBloque, (i + 1) * altoBloque, paint);
                        break;
                    case 2:
                        paint.setColor(Color.YELLOW);
                        canvas.drawCircle((j + 0.5f) * anchoBloque, (i + 0.5f) * altoBloque,
                                Math.min(anchoBloque - 10, altoBloque - 10) / 2, paint);
                        break;
                    case 3:
                        paint.setColor(Color.argb(150, 255, 0, 0));
                        Path path = new Path();
                        path.moveTo(j * anchoBloque, (i + 1) * altoBloque);
                        path.lineTo((j + 1) * anchoBloque, (i + 1) * altoBloque);
                        path.lineTo(j * anchoBloque + (anchoBloque / 2), i * altoBloque);
                        path.close();
                        canvas.drawPath(path, paint);
                        break;
                }
            }
        }
        String textoVidas = "Vidas restantes: " + vidas+"/5";
        paint.setColor(Color.BLACK);
        paint.setTextSize(75);
        canvas.drawText(textoVidas, 20, getHeight() - 550, paint);

        String textoPuntuacion = "Puntuación: " + puntos+"/"+maxPuntos;
        paint.setColor(Color.BLACK);
        paint.setTextSize(75);
        canvas.drawText(textoPuntuacion, 20, getHeight() - 450, paint);

        if (juegoTerminado) {
            String textoVictoria = "¡Has ganado!";
            paint.setColor(Color.GREEN);
            paint.setTextSize(125);
            canvas.drawText(textoVictoria, getWidth() / 2 - 350, getHeight() - 250, paint);
        }
        if (juegoPerdido) {
            String textoDerrota = "¡Has perdido!";
            paint.setColor(Color.RED);
            paint.setTextSize(125);
            canvas.drawText(textoDerrota, getWidth() / 2 - 350, getHeight() - 250, paint);
        }
    }
    private void dibujarBoton(Canvas canvas) {
        if (mostrarBoton) {
            // Dibujar el botón solo cuando mostrarBoton sea true
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setTextSize(75);
            canvas.drawText("Jugar de Nuevo", getWidth() / 2 - 300, getHeight() - 150, paint);
        }
    }
    private void detenerJuego() {
        handler.removeCallbacksAndMessages(null); // Detener los cambios de los triángulos
        mediaPlayerMusicaFondo.stop();
        mediaPlayerMusicaFondo=null;
        mostrarBoton = true; // Mostrar el botón cuando el juego ha terminado
    }
    private void reiniciarJuego() {
        // Reinicia todas las variables y configuraciones del juego
        juegoTerminado = false;
        juegoPerdido = false;
        puntos = 0;
        vidas = 5;
        rellenarPuntos();
        encontrarPosicionInicial(); // Encuentra una nueva posición inicial para el jugador
        cambiarPosiVaciasTriangulos();
        iniciarCambiosTriangulos(); // Reinicia los cambios de los triángulos
        mostrarBoton = false; // Ocultar el botón cuando se reinicia el juego
        mediaPlayerMusicaFondo = MediaPlayer.create(getContext(), R.raw.musica_fondo);
        mediaPlayerMusicaFondo.setLooping(true); // Repetir en bucle
        try {
            mediaPlayerMusicaFondo.start(); // Iniciar la reproducción
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        updateView(); // Actualiza la vista
    }
    public void rellenarPuntos(){
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 5) {
                    laberinto[i][j]=2;
                }
            }
        }
    }
    public void cambiarPosiVaciasTriangulos(){
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 4) {
                   laberinto[i][j]=3;
                }
            }
        }
    }
    public void getMaxPuntos(){
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 2) {
                    maxPuntos++;
                }
            }
        }
    }
    public enum Direccion {
        ARRIBA,
        ABAJO,
        IZQUIERDA,
        DERECHA
    }

}



