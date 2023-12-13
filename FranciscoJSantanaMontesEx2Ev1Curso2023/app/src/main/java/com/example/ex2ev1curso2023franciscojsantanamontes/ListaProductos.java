package com.example.ex2ev1curso2023franciscojsantanamontes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnLongClickListener{
    Adaptador adaptadorLista;
    ArrayList<Producto> listaProductos=new ArrayList<Producto>();
    ListView listaProductosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaproductos);
        TextView nombre=(TextView)findViewById(R.id.user2);
        Intent i=getIntent();
        Bundle extras = i.getExtras();
        String nombreUsuario = extras.getString("NombreU");
        String nombreS = "Usuario: " + nombreUsuario;
        nombre.setText(nombreS);
        TextView contraseña=(TextView)findViewById(R.id.password2);
        Intent i2=getIntent();
        Bundle extras2 = i2.getExtras();
        String passwordUsuario = extras2.getString("Contraseña");
        String passwordS = "Contraseña: " + passwordUsuario;
        contraseña.setText(passwordS);

        listaProductosList=(ListView)findViewById(R.id.listaProductos);
        listaProductos.add(new Producto(R.drawable.altavoz,"Altavoz",33));
        listaProductos.add(new Producto(R.drawable.microfono,"Microfono",14));
        listaProductos.add(new Producto(R.drawable.mousepad,"Mousepad",10));
        listaProductos.add(new Producto(R.drawable.portatil,"Portatil",600));
        listaProductos.add(new Producto(R.drawable.raton,"Raton",20));
        listaProductos.add(new Producto(R.drawable.usb,"USB",70));
        adaptadorLista=new Adaptador(this, R.layout.producto, listaProductos);
        listaProductosList.setAdapter(adaptadorLista);
        listaProductosList.setClickable(true);
        listaProductosList.setOnItemClickListener(this);
        Button btnVolver=(Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view->{
            setResult(RESULT_OK);
            finish();
        });
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String rotate=listaProductos.get(i).getNombre();
    }

    @Override
    public boolean onLongClick(View view) {
        listaProductos.remove(view.getId());
        adaptadorLista.notifyAll();
        return false;
    }
}
