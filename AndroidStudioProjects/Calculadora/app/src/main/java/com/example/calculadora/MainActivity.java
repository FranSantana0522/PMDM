package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Double numero1=0.0;
    private Double numero2=0.0;
    private Integer despuesOpe=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView pantalla=(TextView) findViewById(R.id.pantalla);
        Button n0=(Button) findViewById(R.id.n0);
        Button n1=(Button) findViewById(R.id.n1);
        Button n2=(Button) findViewById(R.id.n2);
        Button n3=(Button) findViewById(R.id.n3);
        Button n4=(Button) findViewById(R.id.n4);
        Button n5=(Button) findViewById(R.id.n5);
        Button n6=(Button) findViewById(R.id.n6);
        Button n7=(Button) findViewById(R.id.n7);
        Button n8=(Button) findViewById(R.id.n8);
        Button n9=(Button) findViewById(R.id.n9);
        Button punto=(Button) findViewById(R.id.punto);
        Button btnC=(Button) findViewById(R.id.borrarTodo);
        Button btnR=(Button) findViewById(R.id.borrarUltimo);
        Button suma=(Button) findViewById(R.id.suma);
        Button resta=(Button) findViewById(R.id.resta);
        Button multi=(Button) findViewById(R.id.multi);
        Button dividir=(Button) findViewById(R.id.dividir);
        Button raiz=(Button) findViewById(R.id.raiz);
        Button igual=(Button) findViewById(R.id.calcular);

        igual.setOnClickListener(view ->{
            String operador;
            for(int i=0;i<pantalla.getText().length();i++){
                operador= String.valueOf(pantalla.getText().charAt(i));
                if(operador=="+"){
                    char text= (char) (numero1+numero2);
                    pantalla.setText(text);
                }else if(operador == "-"){
                    char text= (char) (numero1-numero2);
                    pantalla.setText(text);
                }else if (operador == "*") {
                    char text= (char) (numero1*numero2);
                    pantalla.setText(text);
                }else if(operador=="/"){
                    char text= (char) (numero1/numero2);
                    pantalla.setText(text);
                }
            }

        });
        n0.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                for(int i=0;i<pantalla.getText().length();i++){
                    cons.setCharAt(i, (char) 0);
                }
            }else {
                numero2+=0;
            }
        });
        n1.setOnClickListener(view ->{
            if(despuesOpe==0){
                numero1+=1;
            }else {
                numero2+=1;
            }
        });
        n2.setOnClickListener(view ->{
            if(despuesOpe==0){
                numero1+=2;
            }else {
                numero2+=2;
            }
        });
    }
}