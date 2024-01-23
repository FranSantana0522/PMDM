package com.example.ejercicioerror;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView t,tContador;
    private int cont=0;

    private ImageButton bContinuar,bPausar;

    private Runnable contadorRunnable;

    private Handler handler;

    boolean estaContando=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaramos un Handler del hilo principal
        handler=new Handler(Looper.getMainLooper()) {//usamos para recibir el Message
            @Override
            public void handleMessage(Message msg) {
                // Manejar el mensaje recibido en el hilo principal
                Bundle data = msg.getData();
                String frase = data.getString("palabra");
                Toast.makeText(getApplicationContext(), "Mensaje recibido en el hilo principal: " + frase, Toast.LENGTH_LONG).show();
            }
        };
        setupToolbar();

        mImageView = findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.img1);
        t = findViewById(R.id.textView2);
        tContador=findViewById(R.id.textView3);

        bContinuar=findViewById(R.id.imageButton);
        bPausar=findViewById(R.id.imageButton2);

        bContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!estaContando){
                    iniciarContador();
                }

            }
        });
        bPausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararContador();
            }
        });

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int color = ContextCompat.getColor(this, R.color.blue);
        toolbar.setBackgroundColor(color);
    }



    private void sustituirImg() {
        // POSTDELAYED-->QUITAMOS LA IMG A LOS 5 SEG
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageView.setImageResource(R.drawable.img2);
            }
        },5000);
    }



    private void mnsjHandler(String message) {
        //El método mnsjHandler se utiliza para enviar un mensaje desde un
        //hilo secundario al hilo principal a través de un Handler
        Message msg = new Message();
        Bundle data = new Bundle();
        data.putString("palabra", message);
        msg.setData(data);
        handler.sendMessage(msg);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_send) {
            mnsjHandler("Hola desde un Handler");
            return true;
        }else if(id==R.id.action_UI){
            cambiarTextView();
            return true;
        }else if(id==R.id.action_image){
            sustituirImg();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cambiarTextView() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                t.setText("TAREA COMPLETADA EN EL HILO PRINCIPAL");
            }
        });
    }


    private void iniciarContador() {
        estaContando = true;
        contadorRunnable = new Runnable() {
            @Override
            public void run() {
                contador();
            }
        };
        handler.post(contadorRunnable); // Inicia la tarea
    }


    private void contador(){
        cont++;
        handler.post(new Runnable() {
            @Override
            public void run() {
                tContador.setText("Contador: " + cont);
            }
        });
        handler.postDelayed(contadorRunnable, 1000); // Programa la ejecución del Runnable cada segundo

    }


    // Método para detener la tarea
    private void pararContador() {
        estaContando=false;
        // Eliminar el Runnable de la cola de mensajes pendientes
        handler.removeCallbacks(contadorRunnable);
    }

}
