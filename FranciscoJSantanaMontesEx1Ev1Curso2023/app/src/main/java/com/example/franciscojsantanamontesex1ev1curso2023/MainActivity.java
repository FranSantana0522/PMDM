package com.example.franciscojsantanamontesex1ev1curso2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int volver=1;
    private int jugar=2;
    private EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre=(EditText)findViewById(R.id.nombreU);
        Button btnReglas=(Button) findViewById(R.id.btnR);
        Button btnJugar=(Button) findViewById(R.id.btnJ);

        btnReglas.setOnClickListener(view ->{
            Intent inte =new Intent(this, MainReglas.class);
            startActivityForResult(inte,volver);
        });
        btnJugar.setOnClickListener(view->{
            String nombreUsuario = etNombre.getText().toString();
            Intent inte = new Intent(this, MainJugar.class);
            inte.putExtra("NombreU", nombreUsuario);
            startActivityForResult(inte, jugar);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == volver) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Devuelta a la pagina principal a jugar", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == jugar) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Devuelta a la pagina principal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}