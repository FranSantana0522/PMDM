package com.example.geolocalizacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    double latitude=-1;
    double longitude=-1;
    TextView txtLatitud;
    TextView txtLongitud;
    GoogleMap googleMap;
    SupportMapFragment supportMapFragment;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        txtLatitud=findViewById(R.id.textViewLatitud);
        txtLongitud=findViewById(R.id.textViewLongitud);
        createLocationCallback();
        startLocationUpdates();
        supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
        supportMapFragment.getMapAsync(this);

    }

    //Se llama al actualizarse la ubicación y gestiona lo que se va a hacer con los datos
    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    txtLatitud.setText(String.valueOf(latitude));
                    txtLongitud.setText(String.valueOf(longitude));
                    updateGoogleMaps();
                }
            }
        };
    }

    //Solicita la localización de la ubicación
    private void startLocationUpdates() {
        LocationRequest locationRequest= new LocationRequest.Builder(Priority.PRIORITY_LOW_POWER,1000).build();
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            startLocationUpdates();
        }
    }
    //Sobreescribimos el método onResume para que cuando la aplicación esté en segundo plano se solicitará las actualizaciones de nuevo
    @Override
    public void onResume(){
        super.onResume();
        startLocationUpdates();
    }

    //Sobreescribimos el método onPause para cuando la aplicación pase a un segundo plano que las actualizaciones se detengan
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    //Método que detiene la actualización de la localización cada segundo
    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMapParam) {
        googleMap=googleMapParam;
        //googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));
        //La utilización de setMapStyle es opcional, sirve para personalizar el mapa usando json
    }

    //Método para actualizar el puntero de Google Maps a la par que se actuliza la latitud y longitud
    public void updateGoogleMaps() {
        if (googleMap != null && latitude != -1 && longitude != -1) {
            LatLng ubicacionActual = new LatLng(latitude, longitude);
            googleMap.clear(); // Limpia los marcadores anteriores
            googleMap.addMarker(new MarkerOptions().position(ubicacionActual)); //Añade el marcador
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(ubicacionActual)); //Mueve la cámara hacia el marcador
        }
    }
}
