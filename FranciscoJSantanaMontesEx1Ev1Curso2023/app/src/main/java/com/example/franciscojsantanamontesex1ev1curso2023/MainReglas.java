package com.example.franciscojsantanamontesex1ev1curso2023;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainReglas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglas);
        Button btnVolverReglas=(Button) findViewById(R.id.btnVR);
        btnVolverReglas.setOnClickListener(view->{
            setResult(RESULT_OK);
            finish();
        });
    }
}
