package com.example.runnableehilos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Hilo extends Thread {
    private Handler handler;
    private int maximo, tiempo;

    public Hilo(int n, int t, Handler handler) {
        maximo = n;
        tiempo = t;
        this.handler = handler;
    }

    @Override
    public void run() {
        for (int i = 0; i < maximo; i++) {
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putInt("i", i);
            b.putString("thread", currentThread().toString());
            msg.setData(b);
            handler.sendMessage(msg);
        }
    }
}
