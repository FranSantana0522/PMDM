package com.example.ex1ev2franciscojaviersantanamontes;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ViewHolder> {
    private List<Imagen> imageList;
    private Context context;
    private MediaPlayer mediaPlayer;


    public ImagenAdapter(Context context, List<Imagen> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Imagen imageModel = imageList.get(position);
        holder.imageView.setImageResource(imageModel.getIdImagen());
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                parar();
                return true;
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parar();
                reproducir(imageModel.getIdSonido());
            }
        });
    }
    private void reproducir(int idSonido) {
        parar();
        mediaPlayer = MediaPlayer.create(context, idSonido);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
               reproducir(idSonido);
            }
        });
    }
    private void parar() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

