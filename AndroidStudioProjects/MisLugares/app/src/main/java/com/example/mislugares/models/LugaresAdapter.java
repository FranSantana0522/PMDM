package com.example.mislugares.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mislugares.R;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugarViewHolder> {

    private List<Lugar> listaLugares;
    private LayoutInflater inflater;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(Lugar lugar);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }


    public LugaresAdapter(Context context, List<Lugar> listaLugares) {
        this.inflater = LayoutInflater.from(context);
        this.listaLugares = listaLugares;
    }


    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lugar, parent, false);
        return new LugarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarViewHolder holder, int position) {
        Lugar lugar = listaLugares.get(position);
        holder.bind(lugar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(lugar);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public class LugarViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagenImageView;
        private TextView nombreTextView;
        private RatingBar ratingBar;

        public LugarViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.imagenImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            ratingBar = itemView.findViewById(R.id.barraValoracion);
        }

        public void bind(Lugar lugar) {
            nombreTextView.setText(lugar.getNombre());
            ratingBar.setRating(Float.parseFloat(lugar.getValoracion().toString()));
            TipoLugar tipoLugar = lugar.getTipoLugar();
            switch (tipoLugar) {
                case MONTANIA:
                    imagenImageView.setImageResource(R.drawable.montanas);
                    break;
                case RIO:
                    imagenImageView.setImageResource(R.drawable.rio);
                    break;
                case PLAYA:
                    imagenImageView.setImageResource(R.drawable.barbadosbeach);
                    break;
                case LAGO:
                    imagenImageView.setImageResource(R.drawable.lago);
                    break;
                case ISLA:
                    imagenImageView.setImageResource(R.drawable.islas);
                    break;
                case PRADO:
                    imagenImageView.setImageResource(R.drawable.prado);
                    break;
                case VALLE:
                    imagenImageView.setImageResource(R.drawable.valle);
                    break;
                case PUEBLO:
                    imagenImageView.setImageResource(R.drawable.pueblo);
                    break;
                case CIUDAD:
                    imagenImageView.setImageResource(R.drawable.catedral_sevilla);
                    break;
                default:
                    // En caso de que el tipo de lugar no coincida con ninguno de los casos anteriores, no se establece ninguna imagen.
                    break;
            }
        }
    }
}



