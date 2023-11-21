package com.example.ex3ev1franciscojaviersantanamontes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (esTablet()) {
            setContentView(R.layout.activity_main_tablet);
        } else {
            setContentView(R.layout.activity_main);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            List<Producto> listaDeProductos = obtenerListaDeProductos();
            ProductosAdapter adapter = new ProductosAdapter(listaDeProductos);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener((view, position) -> mostrarDetalleProducto(listaDeProductos.get(position)));

        }
    }
    private List<Producto> obtenerListaDeProductos() {
        // Crea y devuelve una lista de productos (aquí es donde se cargarían tus productos)
        // Por ejemplo:
        return Arrays.asList(
                new Producto("Portátil", 600,"Características de una computadora portátil. \n" +
                        "Una computadora portátil funciona con una batería recargable, contiene una pantalla LED o LCD, teclado, panel táctil o touchpad, una unidad de estado sólido o disco duro, puertos como USB o HDMI, cámara, etc. y puede tener diferentes sistemas operativos como MacOS, Linux, Windows y Chrome OS.\n",R.drawable.portatil),
                new Producto("Altavoz", 33,"Un altavoz (también conocido como parlante, altoparlante, bocina o corneta, mayormente en América del Sur) es un transductor electroacústico, esto es, un dispositivo que convierte una señal eléctrica de audio en ondas mecánicas de sonido.\u200B Un sistema de altavoz, generalmente referido simplemente como altavoz, incluye uno o más transductores, un bafle, conexiones eléctricas, y posiblemente incluya un filtro de cruce.\n",R.drawable.altavoz),
                new Producto("Ratón", 20,"Características de un ratón de ordenador. \n" +
                        "Además de mover un cursor, el mouse tiene uno o más botones para permitir operaciones como la selección de un elemento de menú en una pantalla. El mouse también suele contar con otros elementos, como superficies táctiles y ruedas de desplazamiento, que permiten un control adicional y entrada dimensional.\n",R.drawable.raton),
                new Producto("Ratón", 20,"a al movimiento del puntero en la pantalla.\n" +
                        "MICRÓFONO: Características de los micrófonos.\n" +
                        "Las características principales que debemos valorar a la hora de decantarnos por un micrófono u otro son las siguientes: \n" +
                        "El nivel de presión acústica máxima. \n" +
                        "Nivel de ruido propio. \n" +
                        "Relación señal a ruido. \n" +
                        "Sensibilidad. \n" +
                        "Impedancia. \n" +
                        "Límite de saturación.\n",R.drawable.microfono),
                new Producto("Ratón", 20,"¿Cuál es la función del mouse pad?.\n" +
                        "La alfombrilla de ratón, alfombrilla posa ratón, almohadilla de ratón o mousepad es la superficie sobre la que se apoya y se desliza el ratón o mouse de la computadora, de manera análoga al movimiento del puntero en la pantalla.\n",R.drawable.mousepad),
                new Producto("Ratón", 20," El Bus Universal en Serie (BUS) (en inglés: Universal Serial Bus), más conocido por la sigla USB, es un bus de comunicaciones que sigue un estándar que define los cables, conectores y protocolos usados en un bus para conectar, comunicar y proveer de alimentación eléctrica entre computadoras, periféricos y dispositivos electrónicos.\n",R.drawable.usb)

        );
    }
    private void mostrarDetalleProducto(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Detalles");

        final TextView input = new TextView(MainActivity.this);
        StringBuilder frase=new StringBuilder(producto.getDescripcion());
        input.setText(frase);
        builder.setView(input);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    private boolean esTablet() {
        return getResources().getBoolean(R.bool.es_tablet);
    }
}
