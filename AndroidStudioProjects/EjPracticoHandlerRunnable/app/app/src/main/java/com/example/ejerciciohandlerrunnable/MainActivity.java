package com.example.ejerciciohandlerrunnable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int mDelay = 1000;
    private Bitmap mBitmap;  // Agregado para almacenar la imagen

    private Handler handler = new Handler(Looper.getMainLooper());//inicializamos el handler asociado al hilo principal
//Este Handler se utilizará para realizar operaciones en el hilo principal desde otros hilos.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//creamos el toolbar

        // Obtener el color desde el recurso de colores de manera compatible
        int color = ContextCompat.getColor(this, R.color.blue);

        // Cambiar el color del Toolbar usando el recurso de colores
        toolbar.setBackgroundColor(color);

        mImageView = findViewById(R.id.imageView);
        mTextView = findViewById(R.id.textView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleNavigationItemClick(item.getItemId());
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void handleNavigationItemClick(int itemId) {
        if (itemId == R.id.nav_load) {
            loadIcon();
        } else if (itemId == R.id.nav_other) {
            showWorkingToast();
        } else if (itemId == R.id.nav_clear) {
            clearImage();
        }
    }

    private void loadIcon() {
        Thread newThread = new Thread(new LoadIconTask(R.drawable.ic_launcher_foreground));
        newThread.start();
    }

    private void showWorkingToast() {
        Toast.makeText(MainActivity.this, "Estoy trabajando", Toast.LENGTH_SHORT).show();
    }

    private void clearImage() {
        mImageView.setImageBitmap(null);
        mTextView.setText("Not running yet");
    }

    private class LoadIconTask implements Runnable {
        int resId;

        LoadIconTask(int resId) {
            this.resId = resId;
        }

        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Running");
                }
            });

            mBitmap = BitmapFactory.decodeResource(getResources(), resId);

            for (int i = 1; i < 11; i++) {
                sleep();
                final int step = i;
                Message message = Message.obtain(messageHandler, 1, step, 0);
                messageHandler.sendMessage(message);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mImageView.setImageBitmap(mBitmap);
                    mTextView.setText("Todavia no has pulsado");
                }
            });
        }
    }

    private void sleep() {
        try {
            Thread.sleep(mDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Handler messageHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int step = msg.arg1;
            mTextView.setText("Contador " + step + " segundos");
        }
    };
}
