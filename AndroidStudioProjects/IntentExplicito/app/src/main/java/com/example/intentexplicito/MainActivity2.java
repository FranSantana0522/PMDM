package com.example.intentexplicito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btAceptar=(Button) findViewById(R.id.btAceptar);
        Button btCancelar=(Button) findViewById(R.id.btCancelar);
        EditText etResult=(EditText) findViewById(R.id.etResult);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Si el EditText no está vacío recogemos el resultado.
                if (etResult.getText().length() != 0) { // Guardamos el texto del EditText en un String.
                    String resultado = etResult.getText().toString(); // Recogemos el intent que ha llamado a esta actividad.
                    Intent i = getIntent(); // Le metemos el resultado que queremos mandar a la actividad principal.
                    i.putExtra("RESULTADO", resultado);
                    // Establecemos el resultado, y volvemos a la actividad principal. La variable que introducimos en primer lugar
                    // "RESULT_OK" es de la propia actividad, no tenemos que declararla nosotros.
                    setResult(RESULT_OK, i); // Finalizamos la Activity para volver a la anterior
                    finish();
                } else { // Si no tenía nada escrito el EditText lo avisamos.
                    Toast.makeText(MainActivity2.this, "No se ha introducido nada en el campo de texto",
                    Toast.LENGTH_SHORT).show();
                }
        }
    });
        // Definimos el listener que ejecutará el método onClick del botón cancelar.
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si se pulsa el botón, establecemos el resultado como cancelado. Al igual que con "RESULT_OK", esta variable es de la activity.
                setResult(RESULT_CANCELED); // Finalizamos la Activity para volver a la anterior
                finish();
            }
        });
    }
}
