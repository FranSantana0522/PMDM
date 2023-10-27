package com.example.intentexplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int NOMBRE=1;
    private int APELLIDO=2;
    private EditText etNombre;
    private EditText etApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=(Button) findViewById(R.id.button);
        Button bt2=(Button) findViewById(R.id.button2);
        etNombre=(EditText) findViewById(R.id.etNombre);
        etApellido=(EditText) findViewById(R.id.etApellido);
        bt.setOnClickListener(view -> {
            Intent inte =new Intent(this, MainActivity2.class);
            startActivityForResult(inte,NOMBRE);
        });
        bt2.setOnClickListener(view -> {
            Intent inte =new Intent(this, MainActivity2.class);
            startActivityForResult(inte,APELLIDO);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".
        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT).show();
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("RESULTADO");
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            if(requestCode== NOMBRE){
                etNombre.setText(resultado);
            }else if(requestCode==APELLIDO){
                etApellido.setText(resultado);
            }
        }
    }
}