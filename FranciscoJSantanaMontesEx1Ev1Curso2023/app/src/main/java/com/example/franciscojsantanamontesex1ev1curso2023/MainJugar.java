package com.example.franciscojsantanamontesex1ev1curso2023;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainJugar extends AppCompatActivity {
    Integer turnos=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugar);
        Button btnVolverJugar=(Button) findViewById(R.id.btnVJ);
        TextView nombre=(TextView)findViewById(R.id.textoBienv);
        Intent i=getIntent();
        Bundle extras = i.getExtras();
        String nombreUsuario = extras.getString("NombreU");
        String nombreS = "Bienvenido: " + nombreUsuario;
        nombre.setText(nombreS);

        btnVolverJugar.setOnClickListener(view->{
            setResult(RESULT_OK);
            finish();
        });
        Button btn1=(Button) findViewById(R.id.btn1);
        Button btn2=(Button) findViewById(R.id.btn2);
        Button btn3=(Button) findViewById(R.id.btn3);
        Button btn4=(Button) findViewById(R.id.btn4);
        Button btn5=(Button) findViewById(R.id.btn5);
        Button btn6=(Button) findViewById(R.id.btn6);
        Button btn7=(Button) findViewById(R.id.btn7);
        Button btn8=(Button) findViewById(R.id.btn8);
        Button btn9=(Button) findViewById(R.id.btn9);
        do {
            if (turnos % 2 != 0) {
                btn1.setOnClickListener(view -> {
                    if (ocupado(btn1)) {
                        btn1.setText("x");
                        turnos++;
                    }
                });
                btn2.setOnClickListener(view -> {
                    if (ocupado(btn2)) {
                        btn2.setText("x");
                        turnos++;
                    }
                });
                btn3.setOnClickListener(view -> {
                    if (ocupado(btn3)) {
                        btn3.setText("x");
                        turnos++;
                    }
                });
                btn4.setOnClickListener(view -> {
                    if (ocupado(btn4)) {
                        btn4.setText("x");
                        turnos++;
                    }
                });
                btn5.setOnClickListener(view -> {
                    if (ocupado(btn5)) {
                        btn5.setText("x");
                        turnos++;
                    }
                });
                btn6.setOnClickListener(view -> {
                    if (ocupado(btn6)) {
                        btn6.setText("x");
                        turnos++;
                    }
                });
                btn7.setOnClickListener(view -> {
                    if (ocupado(btn7)) {
                        btn7.setText("x");
                        turnos++;
                    }
                });
                btn8.setOnClickListener(view -> {
                    if (ocupado(btn8)) {
                        btn8.setText("x");
                        turnos++;
                    }
                });
                btn9.setOnClickListener(view -> {
                    if (ocupado(btn9)) {
                        btn9.setText("x");
                        turnos++;
                    }
                });
            } else if (turnos % 2 == 0) {
                do {
                    int ia = (int) (Math.random() * 9 + 1);
                    if (ia == 1 && ocupado(btn1)) {
                        btn1.setText("o");
                        turnos++;
                    }
                    if (ia == 2 && ocupado(btn2)) {
                        btn2.setText("o");
                        turnos++;
                    }
                    if (ia == 3 && ocupado(btn3)) {
                        btn3.setText("o");
                        turnos++;
                    }
                    if (ia == 4 && ocupado(btn4)) {
                        btn4.setText("o");
                        turnos++;
                    }
                    if (ia == 5 && ocupado(btn5)) {
                        btn5.setText("o");
                        turnos++;
                    }
                    if (ia == 6 && ocupado(btn6)) {
                        btn6.setText("o");
                        turnos++;
                    }
                    if (ia == 7 && ocupado(btn7)) {
                        btn7.setText("o");
                        turnos++;
                    }
                    if (ia == 8 && ocupado(btn8)) {
                        btn8.setText("o");
                        turnos++;
                    }
                    if (ia == 9 && ocupado(btn9)) {
                        btn9.setText("o");
                        turnos++;
                    }
                } while (turnos % 2 == 0);
            }
        }while(turnos==9 || victoria());
    }
    public boolean victoria(){
        return false;
    }
    public boolean ocupado(Button botonclickado){
        if(botonclickado.getText().equals("x")){
            Toast.makeText(this, "Casilla ocupada", Toast.LENGTH_SHORT).show();
            return false;
        }else if(botonclickado.getText().equals("o")){
            Toast.makeText(this, "Casilla ocupada", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
