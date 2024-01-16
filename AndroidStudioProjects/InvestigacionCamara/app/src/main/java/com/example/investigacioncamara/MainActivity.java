package com.example.investigacioncamara;

import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import java.io.File;


public class MainActivity extends AppCompatActivity {

    private Camera2Helper mCamera2Helper;
    private TextureView mPreview;
    private MediaRecorder mMediaRecorder;
    private File mOutputFile;
    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private boolean isRecording = false;
    private static final String TAG = "Recorder";
    private Button captureButton;
    private static final String CAMERA_ID = "0"; // ID de la cámara (puede variar según el dispositivo)
    private static final int VIDEO_WIDTH = 1280; // Ancho del video para un dispositivo móvil
    private static final int VIDEO_HEIGHT = 720; // Alto del video para un dispositivo móvil


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreview = findViewById(R.id.surface_view);
        captureButton = findViewById(R.id.button_capture);

        mCamera2Helper = new Camera2Helper(this, mPreview, this);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.RECORD_AUDIO,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    REQUEST_CAMERA_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, proceder con la lógica de la cámara
            } else {
                // Permiso denegado, manejar según la lógica de tu app
            }
        }
    }

    public void onCaptureClick(View view) {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    private void startRecording() {
        mOutputFile = Camera2Helper.createVideoFile(this);
        if (mOutputFile == null) {
            Log.e(TAG, "Error al crear el archivo de video");
            return;
        }
        if (mCamera2Helper.prepareVideoRecorder(this, CAMERA_ID, VIDEO_WIDTH, VIDEO_HEIGHT)) {
            try {
                captureButton.setText("stop");
                mMediaRecorder.start();
                isRecording = true;
            } catch (RuntimeException e) {
                Log.e(TAG, "Error starting MediaRecorder: " + e.getMessage());
                releaseMediaRecorder();
            }
        } else {
            releaseMediaRecorder();
        }
    }

    private void stopRecording() {
        try {
            mMediaRecorder.stop();
        } catch (RuntimeException e) {
            Log.d(TAG, "RuntimeException: stop() is called immediately after start()");
            mOutputFile.delete();
        }
        releaseMediaRecorder();
        mCamera2Helper.releaseCamera();
        captureButton.setText("Capture");
        isRecording = false;
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            try {
                mMediaRecorder.reset();
                mMediaRecorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mMediaRecorder = null;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();
        mCamera2Helper.releaseCamera();
    }

}
