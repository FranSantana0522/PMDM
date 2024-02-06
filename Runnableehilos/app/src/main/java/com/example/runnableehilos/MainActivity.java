package com.example.runnableehilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView=findViewById(R.id.texto);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // Obtener datos del mensaje
                Bundle b = msg.getData();
                String valorString = b.getString("thread");
                int valorI = b.getInt("i");

                // Actualizar el TextView con el valor de i
                textView.append("i=" + valorI + " " + valorString + "\n");

                return true;
            }
        });

        Hilo hilo1 = new Hilo(9, 100, handler);
        Hilo hilo2 = new Hilo(14, 200, handler);
        hilo2.setPriority(7);

        hilo1.start();
        hilo2.start();
    }
}