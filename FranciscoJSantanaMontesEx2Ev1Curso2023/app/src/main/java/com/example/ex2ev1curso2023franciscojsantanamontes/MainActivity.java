package com.example.ex2ev1curso2023franciscojsantanamontes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText user=(EditText) findViewById(R.id.user);
        EditText password=(EditText) findViewById(R.id.password);
        Button validar=(Button) findViewById(R.id.valida);
        ArrayList<Usuario> listaUsuario=new ArrayList<>();
        Usuario a=new Usuario("usuario","usuario");
        Usuario b=new Usuario("Cliente","Cliente");
        Usuario c=new Usuario("internauta","internauta");
        Usuario d=new Usuario("navegante","navegante");
        listaUsuario.add(a);
        listaUsuario.add(b);
        listaUsuario.add(c);
        listaUsuario.add(d);
        validar.setOnClickListener(view->{
            for(int i=0;i<listaUsuario.size();i++){
                String nombre=listaUsuario.get(i).getName();

                if(user.getText().toString().equals(nombre)){

                    for(int j=0;j<listaUsuario.size();i++){
                        String contraseña=listaUsuario.get(i).getPassword();

                        if(password.getText().toString().equals(contraseña)){
                            Intent inte =new Intent(this, ListaProductos.class);
                            inte.putExtra("NombreU", user.getText().toString());
                            inte.putExtra("Contraseña",password.getText().toString());
                            startActivityForResult(inte,1);
                            j=10;
                        }else{
                            user.setText("");
                            password.setText("");
                            Toast.makeText(this, "Contraseña erronea", Toast.LENGTH_SHORT).show();
                        }
                    }
                    i=10;
                }else{
                    user.setText("");
                    password.setText("");
                    Toast.makeText(this, "Usuario erroneo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Devuelta al inicio de sesion", Toast.LENGTH_SHORT).show();
            }
        }
    }
}