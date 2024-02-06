package com.example.imagenesbotones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton goku = findViewById(R.id.goku);
        ImageButton vegeta=findViewById(R.id.vegeta);
        ImageButton naruto=findViewById(R.id.naruto);
        ImageButton saitama=findViewById(R.id.saitama);
        TextView texto = findViewById(R.id.texto);

        goku.setOnClickListener(view->{
            texto.setText("Goku calvo, goku pelon, goku sin pelo");
        });
        vegeta.setOnClickListener(view->{
            texto.setText("Vegeta calvo, vegeta pelon, vegeta sin pelo");
        });
        naruto.setOnClickListener(view->{
            texto.setText("Naruto calvo, naruto pelon, naruto sin pelo");
        });
        saitama.setOnClickListener(view->{
            texto.setText("Saitama, este si es calvo de verdad.");
        });
    }
}