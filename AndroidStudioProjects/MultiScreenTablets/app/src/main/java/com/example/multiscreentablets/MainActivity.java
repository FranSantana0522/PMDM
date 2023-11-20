package com.example.multiscreentablets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutContainer=(LinearLayout) findViewById(R.id.container);
        if(layoutContainer!=null){
            Toast.makeText(this,"Te encuentras en una tablet",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Te encuentras en un movil",Toast.LENGTH_SHORT).show();
        }
    }
}