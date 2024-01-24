package com.example.layouts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.layouts.models.GeoPunto;
import com.example.layouts.models.ListaLugares;
import com.example.layouts.models.Lugar;
import com.example.layouts.models.LugaresAdapter;
import com.example.layouts.models.TipoLugar;
import com.example.layouts.models.repository.ListaLugaresRepository;
import com.example.layouts.models.repository.impl.ListaLugaresRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LugaresAdapter lugaresAdapter;
    private ListaLugares listaLugares;

    private ListaLugaresRepositoryImpl listaLugaresRepositoryImpl;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listaLugares = new ListaLugares(this);

        cargarListaDesdeBD();
        listaLugares.ObtenerListaLugares();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lugaresAdapter = new LugaresAdapter(this, listaLugares.getListaLugares());
        recyclerView.setAdapter(lugaresAdapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cargarListaDesdeBD() {
        listaLugares.añadirLugares(new Lugar("Chipiona","C/ Playa de Regla",new GeoPunto(36.725950, -6.439357),String.valueOf(R.drawable.barbadosbeach),"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Playa de regla de chipiona", LocalDate.of(2024,1,24),4.4, TipoLugar.PLAYA));
        listaLugares.añadirLugares(new Lugar("Sevilla","Av. de la Constitución, 16",new GeoPunto(37.386470, -5.994062),String.valueOf(R.drawable.catedral_sevilla),"https://maps.app.goo.gl/Kh24UZYRR3NWSa8P9","Catedral de Sevilla",LocalDate.of(2024,1,24),4.8,TipoLugar.CIUDAD));
        listaLugares.añadirLugares(new Lugar("Umbrete","C/ Sta. Angela de la Cruz, 2",new GeoPunto(37.369947, -6.157565),String.valueOf(R.drawable.pueblo),"https://maps.app.goo.gl/hCLLM2KwQk4gqggZA","Iglesia Nuestra Señora de la consolidación",LocalDate.of(2024,1,24),4.7,TipoLugar.PUEBLO));
        listaLugares.añadirLugares(new Lugar("Canarias","La Graciosa",new GeoPunto(29.245380, -13.510135),String.valueOf(R.drawable.islas),"https://maps.app.goo.gl/g5yXaT9u69Td83427","Isla La Graciosa",LocalDate.of(2024,1,24),4.6,TipoLugar.ISLA));
        listaLugares.añadirLugares(new Lugar("Torre del Àguila","Torre del Àguila",new GeoPunto(37.051492, -5.751799),String.valueOf(R.drawable.lago),"https://maps.app.goo.gl/LNQusNFPiFJr1wFn8","Embalse de Torre del Àguila",LocalDate.of(2024,1,24),4.4,TipoLugar.LAGO));
        listaLugares.añadirLugares(new Lugar("Mulhacen","Mulhacen",new GeoPunto(37.053154, -3.310956),String.valueOf(R.drawable.montanas),"https://maps.app.goo.gl/CsvFT9TEvFNEeyjR7","Mulhacen, Sierra Nevada",LocalDate.of(2024,1,24),4.8,TipoLugar.MONTANIA));
        listaLugares.añadirLugares(new Lugar("Brazo del este","Brazo del este",new GeoPunto(37.213207, -6.052498),String.valueOf(R.drawable.prado),"https://maps.app.goo.gl/jKC6oavUQ6CGwUc48","Sendero al prado",LocalDate.of(2024,1,24),3.0,TipoLugar.PRADO));
        listaLugares.añadirLugares(new Lugar("Rio Guadalquivir","Sevilla",new GeoPunto(37.369489, -5.992910),String.valueOf(R.drawable.rio),"https://maps.app.goo.gl/TdYZcvUcoNcRD8y46","Rio Guadalquivir al paso de Sevilla",LocalDate.of(2024,1,24),5.0,TipoLugar.RIO));
        listaLugares.añadirLugares(new Lugar("Valle de Cuelgamuros","Valle de Cuelgamuros",new GeoPunto(40.641559, -4.150996),String.valueOf(R.drawable.valle),"https://maps.app.goo.gl/3DgJ38emZrEnt46w6","Monumento",LocalDate.of(2024,1,24),4.4,TipoLugar.VALLE));
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
        if (id == R.id.añadir) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}