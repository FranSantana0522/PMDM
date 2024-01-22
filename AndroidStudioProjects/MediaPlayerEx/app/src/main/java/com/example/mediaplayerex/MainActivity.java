package com.example.mediaplayerex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la referencia del VideoView desde el diseño XML
        videoView = findViewById(R.id.videoView);

        // Establecer la ruta del video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.examenpmdm3; // Reemplaza "tu_video" con el nombre de tu archivo de video

        // Configurar el controlador de medios
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        // Establecer el controlador de medios en el VideoView
        videoView.setMediaController(mediaController);

        // Establecer la fuente del video en el VideoView
        videoView.setVideoPath(videoPath);

        // Iniciar la reproducción del video
        videoView.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Pausar la reproducción del video cuando la actividad está en pausa
        videoView.pause();
    }
}