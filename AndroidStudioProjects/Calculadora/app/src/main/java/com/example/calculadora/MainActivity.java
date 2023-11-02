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
    private Integer useP=0;

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
        punto.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),".");
                pantalla.setText(cons);
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),".");
                pantalla.setText(cons);
            }
        });
        btnR.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            if(cons.length()>1){
                String operador;
                for(int i=0;i<pantalla.getText().length();i++){
                    operador= String.valueOf(pantalla.getText().charAt(i));
                    if(operador.equals("+")){
                        cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                        pantalla.setText(cons);
                        despuesOpe=0;
                    }
                    if(operador.equals("-")){
                        cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                        pantalla.setText(cons);
                        despuesOpe=0;
                    }
                    if (operador.equals("*")) {
                        cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                        pantalla.setText(cons);
                        despuesOpe=0;
                    }
                    if(operador.equals("/")){
                        cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                        pantalla.setText(cons);
                        despuesOpe=0;
                    }
                    if (operador.equals("R")){
                        cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                        pantalla.setText(cons);
                        despuesOpe=0;
                    }
                }
                cons.delete(pantalla.getText().length()-1,pantalla.getText().length());
                pantalla.setText(cons);
            }
        });
        btnC.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.delete(0,pantalla.getText().length());
            cons.insert(0,0);
            pantalla.setText(cons);
            despuesOpe=0;
        });
        suma.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.insert(pantalla.getText().length(),"+");
            pantalla.setText(cons);
            despuesOpe=1;
        });
        resta.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.insert(pantalla.getText().length(),"-");
            pantalla.setText(cons);
            despuesOpe=1;
        });
        multi.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.insert(pantalla.getText().length(),"*");
            pantalla.setText(cons);
            despuesOpe=1;
        });
        dividir.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.insert(pantalla.getText().length(),"/");
            pantalla.setText(cons);
            despuesOpe=1;
        });
        raiz.setOnClickListener(view ->{
            StringBuilder cons= new StringBuilder(pantalla.getText());
            cons.insert(pantalla.getText().length(),"R");
            pantalla.setText(cons);
        });
        igual.setOnClickListener(view ->{
            String operador;
            for(int i=0;i<pantalla.getText().length();i++){
                operador= String.valueOf(pantalla.getText().charAt(i));
                if(operador.equals("+")){
                    StringBuilder text=new StringBuilder();
                    text.insert(0,numero1+numero2);
                    pantalla.setText(text);
                }
                if(operador.equals("-")){
                    StringBuilder text=new StringBuilder();
                    text.insert(0,numero1-numero2);
                    pantalla.setText(text);
                }
                if (operador.equals("*")) {
                    StringBuilder text=new StringBuilder();
                    text.insert(0,numero1*numero2);
                    pantalla.setText(text);
                }
                if(operador.equals("/")){
                    StringBuilder text=new StringBuilder();
                    text.insert(0,numero1/numero2);
                    pantalla.setText(text);
                }
                if (operador.equals("R")){
                    StringBuilder text=new StringBuilder();
                    text.insert(0,Math.sqrt(numero1));
                    pantalla.setText(text);
                }
            }

        });
        n0.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),0);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),0);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n1.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),1);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),1);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n2.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),2);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),2);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n3.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),3);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),3);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n4.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),4);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),4);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n5.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),5);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),5);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n6.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),6);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),6);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n7.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),7);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),7);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n8.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),8);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),8);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
        n9.setOnClickListener(view ->{
            if(despuesOpe==0){
                StringBuilder cons= new StringBuilder(pantalla.getText());
                cons.insert(pantalla.getText().length(),9);
                pantalla.setText(cons);
                numero1= Double.valueOf((String) pantalla.getText());
            }else {
                StringBuilder cons= new StringBuilder();
                cons.insert(0,pantalla.getText());
                cons.insert(cons.length(),9);
                pantalla.setText(cons);
                numero2= Double.valueOf(cons.substring(String.valueOf(numero1).length()));
            }
        });
    }
}