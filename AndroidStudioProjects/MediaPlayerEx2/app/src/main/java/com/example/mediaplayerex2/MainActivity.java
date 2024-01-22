package com.example.mediaplayerex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private Button btnPause;
    private Button btnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alonso_sigma_face));
            mediaPlayer.prepare(); // Puede tardar un tiempo (para el almacenamiento en búfer, etc.)
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPause.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        btnResume.setOnClickListener(view -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // No es necesario implementar para este ejemplo
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        mediaPlayer.release();
    }
}