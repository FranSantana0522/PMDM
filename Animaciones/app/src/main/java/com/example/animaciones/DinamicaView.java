package com.example.animaciones;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DinamicaView extends View implements Runnable{

    float velocidad;
    float s;
    int dt;
    int tiempo;
    boolean continuar;
    int x,y,ymax;
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
            y=y+(int)(velocidad*dt);
            if(y>ymax){
                velocidad=-velocidad;
            }
            if(y<0){
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
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawPaint(paintFondo);
        paint.setTextSize(20 * s);
        canvas.drawCircle(x, y, 30 * s, paintParticula);
        canvas.drawText("y= " + y, 10 * s, 25 * s, paint);
        canvas.drawText("tiempo= " + tiempo, 10 * s, 50 * s, paint);

    }
    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setS(float s) {
        this.s = s;
    }
}
