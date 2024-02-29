package com.example.juegodinosaurio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final int[][] laberinto;
    private final int numeroFilas;
    private final int numeroColumnas;
    private final int anchoBloque;
    private final int altoBloque;
    private int x=40;
    private int y=40;
    private final int ancho=40;
    private final int alto=40;
    private final int movimiento=40;
    public GameSurfaceView(Context context) {
        super(context);
        Laberinto laberintoObj = new Laberinto();
        laberinto = laberintoObj.obtenerLaberinto();
        numeroFilas = laberintoObj.getNumeroFilas();
        numeroColumnas = laberintoObj.getNumeroColumnas();
        anchoBloque = laberintoObj.getAnchoBloque();
        altoBloque = laberintoObj.getAltoBloque();
        // Agregar el Callback a SurfaceHolder para detectar eventos de superficie
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // Obtener el lienzo para dibujar
        Canvas canvas = holder.lockCanvas();
        // Limpiar el lienzo
        canvas.drawColor(Color.WHITE);
        // Crear un objeto Paint para dibujar
        Paint paint = new Paint();
        // Iterar sobre la matriz del laberinto para dibujar los bloques
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                if (laberinto[i][j] == 1) {
                    // Dibujar un bloque negro en la posición (i * anchoBloque, j * altoBloque)
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(j * anchoBloque, i * altoBloque,
                            (j + 1) * anchoBloque, (i + 1) * altoBloque, paint);
                } else if (laberinto[i][j] == 2) {
                    // Dibujar un círculo amarillo en la posición (i * anchoBloque, j * altoBloque)
                    paint.setColor(Color.YELLOW);
                    canvas.drawCircle((j + 0.5f) * anchoBloque, (i + 0.5f) * altoBloque,
                            Math.min(anchoBloque-10, altoBloque-10) / 2, paint);
                }else if (laberinto[i][j] == 3) {
                    Path path = new Path();
                    // Definir los puntos del triángulo (puedes ajustar estos valores según tus necesidades)
                    path.moveTo(j * anchoBloque, (i + 1) * altoBloque); // Punto inferior izquierdo
                    path.lineTo((j + 1) * anchoBloque, (i + 1) * altoBloque); // Punto inferior derecho
                    path.lineTo(j * anchoBloque + (anchoBloque / 2), i * altoBloque); // Punto superior centro
                    path.close(); // Cerrar el contorno del triángulo
                    // Dibujar el triángulo lleno con el color rojo
                    paint.setColor(Color.argb(150, 255, 0, 0));
                    canvas.drawPath(path, paint);
                }
            }
        }
        // Desbloquear el lienzo y mostrar los cambios en la vista
        holder.unlockCanvasAndPost(canvas);
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
