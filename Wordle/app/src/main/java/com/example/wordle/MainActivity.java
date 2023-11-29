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
    ArrayList<Button> listaBtn=new ArrayList<>();
    ArrayList<TextView> listaText=new ArrayList<>();
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaBotones();
        listaTexto();
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
                if (palabra.charAt(pos) == letra.charAt(0)) {
                    botonTurno.setBackgroundColor(getResources().getColor(R.color.verde));
                    for (int j = 0; j < listaText.size(); j++) {
                        if (letra.equals(listaText.get(j).getText().toString())) {
                            listaText.get(j).setBackgroundColor(getResources().getColor(R.color.verde));
                            listaText.remove(j);
                        }
                    }
                } else {
                    botonTurno.setBackgroundColor(getResources().getColor(R.color.naranja));
                    for (int j = 0; j < listaText.size(); j++) {
                        if (letra.equals(listaText.get(j).getText().toString())) {
                            listaText.get(j).setBackgroundColor(getResources().getColor(R.color.naranja));
                        }
                    }
                }
        }else{
            botonTurno.setBackgroundColor(getResources().getColor(R.color.gris));
            for(int j=0;j<listaText.size();j++){
                if(letra.equals(listaText.get(j).getText().toString())){
                    listaText.get(j).setBackgroundColor(getResources().getColor(R.color.gris));
                }
            }
        }
        pos++;
        if(pos>4){
            pos=0;
        }
    }
    public void listaBotones(){
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
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int resId = getResources().getIdentifier("btn" + j + "F" + i, "id", getPackageName());
                Button button = findViewById(resId);
                listaBtn.add(button);
            }
        }
    }
    public  void listaTexto(){
        TextView a=(TextView) findViewById(R.id.tA);
        TextView b=(TextView) findViewById(R.id.tB);
        TextView c=(TextView) findViewById(R.id.tC);
        TextView d=(TextView) findViewById(R.id.tD);
        TextView e=(TextView) findViewById(R.id.tE);
        TextView f=(TextView) findViewById(R.id.tF);
        TextView g=(TextView) findViewById(R.id.tG);
        TextView h=(TextView) findViewById(R.id.tH);
        TextView i=(TextView) findViewById(R.id.tI);
        TextView j=(TextView) findViewById(R.id.tJ);
        TextView k=(TextView) findViewById(R.id.tK);
        TextView l=(TextView) findViewById(R.id.tL);
        TextView m=(TextView) findViewById(R.id.tM);
        TextView n=(TextView) findViewById(R.id.tN);
        TextView ñ=(TextView) findViewById(R.id.tÑ);
        TextView o=(TextView) findViewById(R.id.tO);
        TextView p=(TextView) findViewById(R.id.tP);
        TextView q=(TextView) findViewById(R.id.tQ);
        TextView r=(TextView) findViewById(R.id.tR);
        TextView s=(TextView) findViewById(R.id.tS);
        TextView t=(TextView) findViewById(R.id.tT);
        TextView u=(TextView) findViewById(R.id.tU);
        TextView v=(TextView) findViewById(R.id.tV);
        TextView w=(TextView) findViewById(R.id.tW);
        TextView x=(TextView) findViewById(R.id.tX);
        TextView y=(TextView) findViewById(R.id.tY);
        TextView z=(TextView) findViewById(R.id.tZ);
        TextView[] arrayTextViews = new TextView[27];
        char currentChar = 'A';
        for (int io = 0; io < 27; io++) {
            int resId = getResources().getIdentifier("t" + currentChar, "id", getPackageName());
            arrayTextViews[io] = findViewById(resId);
            listaText.add(arrayTextViews[io]);
            if (currentChar == 'Z') {
                currentChar = 'Ñ';
            } else {
                currentChar++;
            }
        }
    }
}