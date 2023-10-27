package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int fallos=0;
    String finalOculto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView finalText=(TextView) findViewById(R.id.finalText);
        Resources res = getResources();
        String[] palabras = res.getStringArray(R.array.palabras);
        String palabra=palabras[(int) (Math.random()* 5-1)];
        String oculto="";
        for(int i=0;i<palabra.length();i++){
            oculto+="_";
        }
        TextView texto=(TextView) findViewById(R.id.adivinar);
        texto.setText(oculto);

        EditText letra=(EditText) findViewById(R.id.editTextText);
        Button aceptar=(Button) findViewById(R.id.button);
        finalOculto = oculto;
        aceptar.setOnClickListener(view -> {
                String letr= String.valueOf(letra.getText());
                if(palabra.contains(letr)){
                    StringBuilder builder = new StringBuilder(finalOculto);
                    for (int i = 0; i < palabra.length(); i++) {
                        if (palabra.charAt(i) == letr.charAt(0)) {
                            builder.setCharAt(i, letr.charAt(0));
                        }
                    }
                    finalOculto = builder.toString();
                    texto.setText(finalOculto);
                    Toast.makeText(this,"Has acertado",Toast.LENGTH_SHORT).show();
                    if(finalOculto.equals(palabra)){
                        finalText.setVisibility(View.VISIBLE);
                        letra.setVisibility(View.GONE);
                        aceptar.setVisibility(View.INVISIBLE);
                    }
                }else{
                    Toast.makeText(this,"Has fallado",Toast.LENGTH_SHORT).show();
                    fallos++;
                    ImageView imagen=(ImageView) findViewById(R.id.imageView);
                    if(fallos==1){
                        imagen.setImageResource(R.drawable.hangman2);
                    }else if(fallos==2){
                        imagen.setImageResource(R.drawable.hangman3);
                    }else if(fallos==3){
                        imagen.setImageResource(R.drawable.hangman4);
                    }else if(fallos==4){
                        imagen.setImageResource(R.drawable.hangman5);
                    }else if(fallos==5){
                        imagen.setImageResource(R.drawable.hangman6);
                    }else if(fallos==6){
                        imagen.setImageResource(R.drawable.hangman7);
                    }else if(fallos==7){
                        imagen.setImageResource(R.drawable.hangman8);
                        finalText.setText("Has perdido :(");
                        finalText.setVisibility(View.VISIBLE);
                        letra.setVisibility(View.GONE);
                        aceptar.setVisibility(View.INVISIBLE);
                    }
                }
        });

    }
}