package com.example.radiocheck;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup r=(RadioGroup)findViewById(R.id.radioGroup);
        r.setOnCheckedChangeListener(this);
        CheckBox b=(CheckBox)findViewById(R.id.checkBox);
        b.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton c, boolean b){
        TextView t=(TextView)findViewById(R.id.estado);

        if(b)
            t.setText("Te gusta el fútbol!!");
        else
            t.setText("No te gusta el fútbol?!??!!");
    }

    public void onCheckedChanged(RadioGroup r,int a){
        TextView t=(TextView)findViewById(R.id.estado);
        if (a == R.id.radioButton2) { // Talavera
            t.setText("Buena elección!: El Talavera promete!!");
        } else if (a == R.id.radioButton3) { // Alcazar
            t.setText("Gran equipo la gimnástica!!");
        } else if (a == R.id.radioButton) { // Albacete
            t.setText("El Albacete no es el mismo desde que se fue Iniesta");
        } else if (a == R.id.radioButton4) { // Otros
            t.setText("El dinero no lo es todo....");
        }
    }
}