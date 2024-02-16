package com.example.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;
    private Paint paint;
    private float playerX, playerY;
    public GameSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
// Configuración de eventos táctiles y de teclado
        setFocusable(true);
// Inicialización de objetos y recursos
        paint = new Paint();
        paint.setColor(Color.RED);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
// Inicialización de hilo de juego
        gameThread = new GameThread(holder);
        gameThread.setRunning(true);
        gameThread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
// Ajustes cuando cambia el tamaño
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
// Detener el hilo de juego y liberar recursos
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } } }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
// Manejar eventos táctiles
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                playerX = event.getX();
                playerY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                playerX = event.getX();
                playerY = event.getY();
                break;
        }
        return true;
    }
    private class GameThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private boolean running = false;
        public GameThread(SurfaceHolder holder) {
            surfaceHolder = holder;
        }
        public void setRunning(boolean run) {
            running = run;
        }
        @Override
        public void run() {
            while (running) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
// Operaciones de dibujo en el lienzo
                    drawGame(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } } } }
    private void drawGame(Canvas canvas) {
// Dibujar en el canvas
        canvas.drawColor(Color.BLACK); // Fondo negro
        canvas.drawCircle(playerX, playerY, 50, paint); // Jugador como un círculo rojo
    }
}