package com.example.audiocontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private Button btnStart,btnStop,btnResume,btnBack,btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart=findViewById(R.id.startMedia);
        btnStop=findViewById(R.id.stopMedia);
        btnResume=findViewById(R.id.resetMedia);
        btnBack=findViewById(R.id.rebootMedia);
        btnJump=findViewById(R.id.jumpMedia);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gangamstyle));
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start();
            });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al preparar el reproductor de medios", Toast.LENGTH_SHORT).show();
        }



        btnStart.setOnClickListener(view ->{
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        });

        btnStop.setOnClickListener(view ->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btnStop.setText("Continuar");
            }else{
                mediaPlayer.start();
                btnStop.setText("Parar");
            }
        });

        btnResume.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {
                mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gangamstyle));
                mediaPlayer.prepareAsync();

                mediaPlayer.setOnPreparedListener(mp -> {
                    mediaPlayer.start();
                });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al preparar el reproductor de medios", Toast.LENGTH_SHORT).show();
            }
        });



        btnBack.setOnClickListener(view->{
            int rewindPosition = mediaPlayer.getCurrentPosition() - 10000;
            if(rewindPosition > 0) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(rewindPosition);
                mediaPlayer.start();
            }
        });

        btnJump.setOnClickListener(view->{
            int duration = mediaPlayer.getDuration();
            int posicionAvanzar = mediaPlayer.getCurrentPosition() + 10000;
            if(posicionAvanzar < duration){
                mediaPlayer.pause();
                mediaPlayer.seekTo(posicionAvanzar);
                mediaPlayer.start();
            }
        });
    }
}