package com.example.grafico4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GraficoView extends View {

    public GraficoView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int canvasWidth;
        int canvasHeight;
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2 * getResources().getDisplayMetrics().density);
        canvasWidth = getMeasuredWidth();
        canvasHeight = getMeasuredHeight();
        canvas.drawColor(Color.YELLOW);
        canvas.drawLine(0, 0, canvasWidth, canvasHeight, paint);
        canvas.drawLine(canvasWidth, 0, 0, canvasHeight, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        float textHeight = paint.descent() - paint.ascent();
        float y = canvasHeight / 2 - (textHeight * 2);
        String widthText = "Width: " + canvasWidth;
        String heightText = "Height: " + canvasHeight;
        String bottomText = "Bottom: " + getBottom();
        String rightText = "Right: " + getRight();
        canvas.drawText(widthText, (canvasWidth - paint.measureText(widthText)) / 2, y, paint);
        y += textHeight * 2;
        canvas.drawText(heightText, (canvasWidth - paint.measureText(heightText)) / 2, y, paint);
        y += textHeight * 2;
        canvas.drawText(bottomText, (canvasWidth - paint.measureText(bottomText)) / 2, y, paint);
        y += textHeight * 2;
        canvas.drawText(rightText, (canvasWidth - paint.measureText(rightText)) / 2, y, paint);
    }
}
