package com.example.mislugares;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.example.mislugares.fragments.EditarLugar;
import com.example.mislugares.fragments.detalleLugar;
import com.example.mislugares.models.GeoPunto;
import com.example.mislugares.models.ListaLugares;
import com.example.mislugares.models.Lugar;
import com.example.mislugares.models.LugaresAdapter;
import com.example.mislugares.models.TipoLugar;
import com.example.mislugares.settings.SettingsActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mislugares.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity implements detalleLugar.OnLugarChangeListener{

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Lugar lugar;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        temaOscuro();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.fragmentPrincipal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

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
            NavController navController = Navigation.findNavController(this,R.id.fragmentPrincipal);
            navController.navigate(R.id.anadirLugar);
            return true;
        }
        if(id == R.id.preferencias){
            NavController navController = Navigation.findNavController(this,R.id.fragmentPrincipal);
            navController.navigate(R.id.settingsFragment);
            return  true;
        }

        if(id == R.id.acercaDe){
            NavController navController = Navigation.findNavController(this,R.id.fragmentPrincipal);
            navController.navigate(R.id.acercaDe2);
            return  true;
        }
        if(id == R.id.editarLugar){
            EditarLugar editarLugar = new EditarLugar();
            Bundle bundle = new Bundle();
            bundle.putSerializable("editarLugar", lugar);
            editarLugar.setArguments(bundle);
            NavController navController = Navigation.findNavController(this, R.id.fragmentPrincipal);
            navController.navigate(R.id.editarLugar2, bundle);
        }

        return super.onOptionsItemSelected(item);
    }

    private void temaOscuro() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("pref_key_dark_theme", false);
        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentPrincipal);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onLugarChanged(Lugar lugar) {
        this.lugar = lugar;
    }
}