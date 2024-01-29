package com.example.mislugares;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mislugares.models.GeoPunto;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.TipoLugar;

import java.time.LocalDate;

public class Aplicacion extends Application {
    public ListaLugares listaLugares;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        listaLugares=ListaLugares.getInstance(this);
        cargarListaDesdeBD(listaLugares);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cargarListaDesdeBD(ListaLugares listaLugares) {
        listaLugares.añadirLugares(new Lugar("Chipiona","C/ Playa de Regla",new GeoPunto(36.725950, -6.439357),getResources().getResourceEntryName(R.drawable.barbadosbeach),"https://maps.app.goo.gl/UymTwpkZpCJv1zpG6","Playa de regla de chipiona", LocalDate.of(2024,1,24),4.4, TipoLugar.PLAYA));
        listaLugares.añadirLugares(new Lugar("Sevilla","Av. de la Constitución, 16",new GeoPunto(37.386470, -5.994062),getResources().getResourceEntryName(R.drawable.catedral_sevilla),"https://maps.app.goo.gl/Kh24UZYRR3NWSa8P9","Catedral de Sevilla",LocalDate.of(2024,1,24),4.8, TipoLugar.CIUDAD));
        listaLugares.añadirLugares(new Lugar("Umbrete","C/ Sta. Angela de la Cruz, 2",new GeoPunto(37.369947, -6.157565),getResources().getResourceEntryName(R.drawable.pueblo),"https://maps.app.goo.gl/hCLLM2KwQk4gqggZA","Iglesia Nuestra Señora de la consolidación",LocalDate.of(2024,1,24),4.7,TipoLugar.PUEBLO));
        listaLugares.añadirLugares(new Lugar("Canarias","La Graciosa",new GeoPunto(29.245380, -13.510135),getResources().getResourceEntryName(R.drawable.islas),"https://maps.app.goo.gl/g5yXaT9u69Td83427","Isla La Graciosa",LocalDate.of(2024,1,24),4.6,TipoLugar.ISLA));
        listaLugares.añadirLugares(new Lugar("Torre del Àguila","Torre del Àguila",new GeoPunto(37.051492, -5.751799),getResources().getResourceEntryName(R.drawable.lago),"https://maps.app.goo.gl/LNQusNFPiFJr1wFn8","Embalse de Torre del Àguila",LocalDate.of(2024,1,24),4.4,TipoLugar.LAGO));
        listaLugares.añadirLugares(new Lugar("Mulhacen","Mulhacen",new GeoPunto(37.053154, -3.310956),getResources().getResourceEntryName(R.drawable.montanas),"https://maps.app.goo.gl/CsvFT9TEvFNEeyjR7","Mulhacen, Sierra Nevada",LocalDate.of(2024,1,24),4.8,TipoLugar.MONTANIA));
        listaLugares.añadirLugares(new Lugar("Brazo del este","Brazo del este",new GeoPunto(37.213207, -6.052498),getResources().getResourceEntryName(R.drawable.prado),"https://maps.app.goo.gl/jKC6oavUQ6CGwUc48","Sendero al prado",LocalDate.of(2024,1,24),3.0,TipoLugar.PRADO));
        listaLugares.añadirLugares(new Lugar("Rio Guadalquivir","Sevilla",new GeoPunto(37.369489, -5.992910),getResources().getResourceEntryName(R.drawable.rio),"https://maps.app.goo.gl/TdYZcvUcoNcRD8y46","Rio Guadalquivir al paso de Sevilla",LocalDate.of(2024,1,24),5.0,TipoLugar.RIO));
        listaLugares.añadirLugares(new Lugar("Valle de Cuelgamuros","Valle de Cuelgamuros",new GeoPunto(40.641559, -4.150996),getResources().getResourceEntryName(R.drawable.valle),"https://maps.app.goo.gl/3DgJ38emZrEnt46w6","Monumento",LocalDate.of(2024,1,24),4.4,TipoLugar.VALLE));

    }
}
