package com.example.graficos7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GraficoView extends View
{

    public GraficoView(Context context) {
        super(context);
    }
    protected void onDraw(Canvas canvas){

        float s=getResources().getDisplayMetrics().scaledDensity;
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(30*s);
        canvas.save();
        float x=50*s;
        float y=150*s;
        canvas.translate(x,y);
        String texto="Rotación de un canvas";
        Rect bounds=new Rect();
        paint.getTextBounds(texto,0,texto.length(),bounds);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2*s);
        canvas.drawRect(bounds,paint);
        float centreX=bounds.exactCenterX();
        float centreY=bounds.exactCenterY();
        canvas.rotate(-45,centreX,centreY);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(texto,0,0,paint);
        canvas.restore();
        canvas.drawText("Tras la rotación",50*s,300*s,paint);
    }
}
