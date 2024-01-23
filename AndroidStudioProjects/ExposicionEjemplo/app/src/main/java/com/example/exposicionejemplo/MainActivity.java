package com.example.exposicionejemplo;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la referencia del VideoView desde el diseño
        videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.sample;
        Uri uri = Uri.parse(videoPath);

        // Configurar MediaController para agregar controles de reproducción
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Establecer la URI del video al VideoView
        videoView.setVideoURI(uri);

        // Iniciar la reproducción del video
        videoView.start();

        // Botones flotantes y TextView
        FloatingActionButton fabControles = findViewById(R.id.fabControles);
        fabControles.setOnClickListener(v -> {
            mediaController.show(5000);
        });

        FloatingActionButton fabEsconder = findViewById(R.id.fabEsconder);
        fabEsconder.setOnClickListener(v -> {
            mediaController.hide();
        });

        FloatingActionButton fabDetener = findViewById(R.id.fabDetener);
        fabDetener.setOnClickListener(v -> {
            videoView.stopPlayback();
        });

        FloatingActionButton fabIrA = findViewById(R.id.fabIrA);


        fabIrA.setOnClickListener(v -> {
            videoView.seekTo(15000);
        });

        FloatingActionButton fabReiniciar = findViewById(R.id.fabReiniciar);
        fabReiniciar.setOnClickListener(v -> {
            videoView.resume();
        });

        // Configurar listeners para avanzar y retroceder
        mediaController.setPrevNextListeners(
                v -> {
                    // Lógica para avanzar
                    videoView.seekTo(videoView.getCurrentPosition() + 5000);
                    Toast.makeText(this, "Avanzado 5 segundos", Toast.LENGTH_SHORT).show();
                },
                v -> {
                    // Lógica para retroceder
                    videoView.seekTo(videoView.getCurrentPosition() - 5000);
                    Toast.makeText(this, "Retrocedido 5 segundos", Toast.LENGTH_SHORT).show();
                });
    }
}
