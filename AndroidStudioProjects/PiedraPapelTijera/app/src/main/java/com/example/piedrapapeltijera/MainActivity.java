package com.example.piedrapapeltijera;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imagePaper=(ImageButton) findViewById(R.id.imagePaper);
        ImageButton imageRock=(ImageButton) findViewById(R.id.imageRock);
        ImageButton imageScissors=(ImageButton) findViewById(R.id.imageScissors);
        TextView textFinal=(TextView) findViewById(R.id.textFinal);
        ImageView imagePC=(ImageView) findViewById(R.id.imagePC);
        imagePaper.setOnClickListener(view ->{
            randomNum=(int)(Math.random()*3+1);
            if(randomNum==1){
                imagePC.setImageResource(R.drawable.paper_pc);
                textFinal.setText("Empate");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==2){
                imagePC.setImageResource(R.drawable.rock_pc);
                textFinal.setText("¡Has Ganado!");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==3){
                imagePC.setImageResource(R.drawable.scissors_pc);
                textFinal.setText("Has perdido :(");
                textFinal.setVisibility(View.VISIBLE);
            }
        });
        imageRock.setOnClickListener(view ->{
            randomNum=(int)(Math.random()*3+1);
            if(randomNum==1){
                imagePC.setImageResource(R.drawable.paper_pc);
                textFinal.setText("Has perdido :(");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==2){
                imagePC.setImageResource(R.drawable.rock_pc);
                textFinal.setText("Empate");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==3){
                imagePC.setImageResource(R.drawable.scissors_pc);
                textFinal.setText("¡Has Ganado!");
                textFinal.setVisibility(View.VISIBLE);
            }
        });
        imageScissors.setOnClickListener(view ->{
            randomNum=(int)(Math.random()*3+1);
            if(randomNum==1){
                imagePC.setImageResource(R.drawable.paper_pc);
                textFinal.setText("¡Has Ganado!");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==2){
                imagePC.setImageResource(R.drawable.rock_pc);
                textFinal.setText("Has perdido :(");
                textFinal.setVisibility(View.VISIBLE);
            }else if(randomNum==3){
                imagePC.setImageResource(R.drawable.scissors_pc);
                textFinal.setText("Empate");
                textFinal.setVisibility(View.VISIBLE);
            }
        });
    }
}