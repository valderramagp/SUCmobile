package suc.itmotions.net.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import suc.itmotions.net.DetalleAsignaturas;
import suc.itmotions.net.R;
import suc.itmotions.net.entities.Asignatura;
import suc.itmotions.net.entities.DetalleAsignatura;

/**
 * Created by gusta on 11/04/2016.
 */
public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.AsignaturasViewHolder> implements ItemClickListener{

    private Context context;

    private final List<Asignatura> asignaturas;


    public AdaptadorAsignaturas(List<Asignatura> asignaturas, Context context) {
        this.asignaturas = asignaturas;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return asignaturas.size();
    }

    @Override
    public AsignaturasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new AsignaturasViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(AsignaturasViewHolder holder, int position) {
        holder.tvTitle.setText(asignaturas.get(position).getMateria());
        holder.tvDescription.setText(asignaturas.get(position).getProfesor());
    }

    @Override
    public void onItemClick(View view, int position) {
        DetalleAsignaturas.launch((Activity) context, asignaturas.get(position).getId());
    }

    public static class AsignaturasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle,tvDescription, id;
        public CardView cardView;
        public ItemClickListener listener;

        public AsignaturasViewHolder(View itemView, ItemClickListener listener){
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}

interface ItemClickListener {
    void onItemClick(View view, int position);
}
