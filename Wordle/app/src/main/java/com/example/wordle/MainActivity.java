package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Integer cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1F1=(Button) findViewById(R.id.btn1F1);
        Button btn2F1=(Button) findViewById(R.id.btn2F1);
        Button btn3F1=(Button) findViewById(R.id.btn3F1);
        Button btn4F1=(Button) findViewById(R.id.btn4F1);
        Button btn5F1=(Button) findViewById(R.id.btn5F1);
        Button btn1F2=(Button) findViewById(R.id.btn1F2);
        Button btn2F2=(Button) findViewById(R.id.btn2F2);
        Button btn3F2=(Button) findViewById(R.id.btn3F2);
        Button btn4F2=(Button) findViewById(R.id.btn4F2);
        Button btn5F2=(Button) findViewById(R.id.btn5F2);
        Button btn1F3=(Button) findViewById(R.id.btn1F3);
        Button btn2F3=(Button) findViewById(R.id.btn2F3);
        Button btn3F3=(Button) findViewById(R.id.btn3F3);
        Button btn4F3=(Button) findViewById(R.id.btn4F3);
        Button btn5F3=(Button) findViewById(R.id.btn5F3);
        Button btn1F4=(Button) findViewById(R.id.btn1F4);
        Button btn2F4=(Button) findViewById(R.id.btn2F4);
        Button btn3F4=(Button) findViewById(R.id.btn3F4);
        Button btn4F4=(Button) findViewById(R.id.btn4F4);
        Button btn5F4=(Button) findViewById(R.id.btn5F4);
        Button btn1F5=(Button) findViewById(R.id.btn1F5);
        Button btn2F5=(Button) findViewById(R.id.btn2F5);
        Button btn3F5=(Button) findViewById(R.id.btn3F5);
        Button btn4F5=(Button) findViewById(R.id.btn4F5);
        Button btn5F5=(Button) findViewById(R.id.btn5F5);
        ArrayList<Button> listaBtn=new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int resId = getResources().getIdentifier("btn" + j + "F" + i, "id", getPackageName());
                Button button = findViewById(resId);
                listaBtn.add(button);
            }
        }
        Resources res = getResources();
        String [] listaPalabras=res.getStringArray(R.array.listaPalabras);
        String palabra=listaPalabras[(int) (Math.random() * 5)];

        EditText letraIntro=(EditText) findViewById(R.id.letraIntro);
        TextView textowin=(TextView) findViewById(R.id.textowin);
        Button accept=(Button) findViewById(R.id.accept);

        accept.setOnClickListener(view->{
            String letra=letraIntro.getText().toString();
            letra= String.valueOf(letra.charAt(0));
            letraCambio(listaBtn.get(cont),letra, palabra);
            cont++;
        });

    }
    public void letraCambio(Button botonTurno, String letra, String palabra){
        letra= String.valueOf(letra.charAt(0));
        letra=letra.toUpperCase();
        botonTurno.setText(letra);
        if(palabra.contains(letra)) {
            for(int i=0;i<palabra.length();i++){
               if(palabra.charAt(i)==letra.charAt(0)){
                   botonTurno.setBackgroundColor(getResources().getColor(R.color.verde));
                   i=10;
               }else {
                   botonTurno.setBackgroundColor(getResources().getColor(R.color.naranja));
                   i=10;
               }
            }
        }else{
            botonTurno.setBackgroundColor(getResources().getColor(R.color.gris));
        }
    }
}