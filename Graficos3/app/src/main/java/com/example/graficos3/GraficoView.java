package com.example.graficos3;

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
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        float textSize = 40;
        float lineSpacing;
        int numLines = 10;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setTextSize(textSize);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        lineSpacing = (float) canvasHeight / (numLines + 1);

        for (int i = 1; i <= numLines; i++) {
            float y = i * lineSpacing;
            canvas.drawLine(0, y, canvasWidth, y, paint);
            float scaleValue = 1.0f - (y / canvasHeight);
            canvas.drawText(String.format("%.2f", scaleValue), canvasWidth + 20, y + textSize / 2, paint);
        }
        float textY = textSize;
        String densityInfo = "Density: " + getResources().getDisplayMetrics().density;
        String widthInfo = "Width: " + canvasWidth;
        String heightInfo = "Height: " + canvasHeight;
        String aspectRatioInfo = "Aspect Ratio: " + ((float) canvasHeight / canvasWidth);
        canvas.drawText(densityInfo, 20, textY, paint);
        textY += textSize * 1.5;
        canvas.drawText(widthInfo, 20, textY, paint);
        textY += textSize * 1.5;
        canvas.drawText(heightInfo, 20, textY, paint);
        textY += textSize * 1.5;
        canvas.drawText(aspectRatioInfo, 20, textY, paint);
    }
}
