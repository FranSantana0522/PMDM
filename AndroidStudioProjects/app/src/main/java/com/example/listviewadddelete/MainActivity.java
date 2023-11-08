package com.example.listviewadddelete;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.listviewadddelete.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;
    AdaptadorLista adaptadorLista;
    ArrayList<Persona> listaPersona=new ArrayList<>();
    Persona persona;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "No funciona", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        listView=(ListView)findViewById(R.id.lista);
        listaPersona.add(new Persona("Francisco",R.drawable.jijija));
        adaptadorLista=new AdaptadorLista(MainActivity.this,listaPersona);
        listView.setAdapter(adaptadorLista);
        listView.setClickable(true);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Eliminar Persona");

        final TextView input = new TextView(MainActivity.this);
        StringBuilder frase=new StringBuilder("   ¿Estas seguro de que quieres eliminar a "+listaPersona.get(position).getNombre()+"?");
        input.setText(frase);
        builder.setView(input);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaPersona.remove(position);
                adaptadorLista.notifyDataSetChanged();
                Snackbar.make(findViewById(android.R.id.content), "Se ha eliminado correctamente", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Cerrar el cuadro de diálogo si se hace clic en "Cancelar".
            }
        });
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.boton_anadir) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Agregar Persona");

            final EditText input = new EditText(MainActivity.this);
            builder.setView(input);

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String nombre = input.getText().toString();
                    listaPersona.add(new Persona(nombre, R.drawable.jijija));
                    adaptadorLista.notifyDataSetChanged();
                    Snackbar.make(findViewById(android.R.id.content), "Se ha añadido correctamente", Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.fab)
                            .setAction("Action", null).show();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel(); // Cerrar el cuadro de diálogo si se hace clic en "Cancelar".
                }
            });
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}