package com.example.trabajoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.usernameTextView);

        mostrarUser();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void mostrarUser() {
        boolean isFeatureEnabled = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("pref_key_enable_feature", false);

        if (isFeatureEnabled) {

            String username = PreferenceManager.getDefaultSharedPreferences(this)
                    .getString("pref_key_username", "");
            user.setText("Usuario: " + username);
        } else {

            user.setText("               ");
        }
    }
}