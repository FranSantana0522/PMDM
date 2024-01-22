package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText user, password;
    private CheckBox casillaRec;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.editTextUsuario);
        password = findViewById(R.id.editTextClave);
        casillaRec = findViewById(R.id.checkBoxRecordar);
        Button btnInicioSesion = findViewById(R.id.btnInicioSesion);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        boolean recordarSesion = sharedPreferences.getBoolean("recordar_sesion", false);
        casillaRec.setChecked(recordarSesion);
        if (recordarSesion) {
            String saveUser = sharedPreferences.getString("usuario", "");
            String savePassword = sharedPreferences.getString("clave", "");
            user.setText(saveUser);
            password.setText(savePassword);
        }

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = user.getText().toString();
                String clave = password.getText().toString();
                if (casillaRec.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", usuario);
                    editor.putString("clave", clave);
                    editor.putBoolean("recordar_sesion", true);
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("usuario");
                    editor.remove("clave");
                    editor.putBoolean("recordar_sesion", false);
                    editor.apply();
                }
                login();
            }
        });
    }
    private void login() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
