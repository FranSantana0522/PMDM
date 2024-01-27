package com.example.layouts.settings;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.layouts.MainActivity;
import com.example.layouts.settings.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity implements SettingsFragment.OnDataPass{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public void onDataPass(Bundle bundle) {
        if (bundle != null) {
            String data = bundle.getString("clave");
            if (data != null) {
                Log.d("Estoy en el settingsActivity", data);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("clave", data);
                intent.putExtra("borrar_datos", bundle.getBoolean("borrar_datos"));
                Log.d("Estoy en el settingsActivity", intent.toString());
                startActivity(intent);
            } else {
                Log.d("Estoy en el settingsActivity", "El contenido del Bundle es nulo");
            }
        } else {
            Log.d("Estoy en el settingsActivity", "El Bundle recibido es nulo");
        }
    }


}
