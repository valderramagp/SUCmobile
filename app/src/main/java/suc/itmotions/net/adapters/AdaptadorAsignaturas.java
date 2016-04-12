package suc.itmotions.net.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import suc.itmotions.net.R;
import suc.itmotions.net.entities.Asignatura;

/**
 * Created by gusta on 11/04/2016.
 */
public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.AsignaturasViewHolder> {

    private final List<Asignatura> asignaturas;


    public AdaptadorAsignaturas(List<Asignatura> asignaturas, OnItemClickListener escuchaClicksExterna) {
        this.asignaturas = asignaturas;
        this.escuchaClicksExterna = escuchaClicksExterna;
    }

    @Override
    public AsignaturasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        AsignaturasViewHolder viewHolder = new AsignaturasViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AsignaturasViewHolder holder, int position) {
        holder.tvTitle.setText(asignaturas.get(position).getMateria());
        holder.tvDescription.setText(asignaturas.get(position).getProfesor());
    }

    @Override
    public int getItemCount() {
        if (asignaturas != null) {
            return asignaturas.size() > 0 ? asignaturas.size() : 0;
        } else {
            return 0;
        }
    }

    private String obtenerIdAsignatura(int posicion) {
        if (posicion != RecyclerView.NO_POSITION) {
            return asignaturas.get(posicion).getId();
        } else {
            return null;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class AsignaturasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle,tvDescription, id;
        public CardView cardView;
        public AsignaturasViewHolder(View itemView){
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            escuchaClicksExterna.onClick(this, obtenerIdAsignatura(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        public void onClick(AsignaturasViewHolder viewHolder, String idAsignatura);
    }

    private OnItemClickListener escuchaClicksExterna;
}
