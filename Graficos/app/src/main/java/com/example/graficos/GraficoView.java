package com.example.graficos;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class GraficoView extends View {

    public GraficoView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.rgb(100, 20, 10));
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            float s = getResources().getDisplayMetrics().scaledDensity;
            paint.setAntiAlias(true);
            paint.setTextSize(20 * s);
            canvas.drawText("width: " + width + "height: " + height, 20 *s,
                    40 * s, paint);
            canvas.drawText("escala: " + s, 20 * s, 140 * s, paint);
            paint.setColor(Color.BLUE);
            canvas.drawLine(0, 40 * s, width, 40 * s, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawLine(20 * s, 0, 20 * s, height, paint);
        }
}
