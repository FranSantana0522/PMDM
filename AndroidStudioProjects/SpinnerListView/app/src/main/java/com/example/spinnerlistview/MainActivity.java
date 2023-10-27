package com.example.spinnerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Spinner spinner;
    private ListView lista;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        spinner=(Spinner) findViewById(R.id.spinner);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        text.setText("Has seleccionado: "+ adapter.getItem(position));
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                        text.setText(R.string.app_name);
                    }
                });
        lista=(ListView) findViewById(R.id.lista);
        ArrayAdapter<CharSequence> adaptador= ArrayAdapter.createFromResource(this,R.array.lista, android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        text.setText("Has seleccionado: "+ adapterView.getAdapter().getItem(i));
    }

}