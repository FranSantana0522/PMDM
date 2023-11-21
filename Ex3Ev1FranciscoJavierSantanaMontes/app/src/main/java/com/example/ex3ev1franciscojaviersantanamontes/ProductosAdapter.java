package com.example.ex3ev1franciscojaviersantanamontes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    private List<Producto> listaDeProductos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombreProducto;
        TextView precioProducto;

        public ProductoViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            imagen=itemView.findViewById(R.id.imagen_producto);
            nombreProducto = itemView.findViewById(R.id.nombre_producto);
            precioProducto = itemView.findViewById(R.id.precio_producto);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView, position);
                    }
                }
            });
        }
    }

    public ProductosAdapter(List<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_producto, parent, false);
        return new ProductoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaDeProductos.get(position);

        holder.nombreProducto.setText(producto.getNombre());
        holder.precioProducto.setText(String.valueOf(producto.getPrecio()));
        holder.imagen.setImageResource(producto.getImagen());
    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }
}

