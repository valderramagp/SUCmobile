package suc.itmotions.net.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import suc.itmotions.net.R;
import suc.itmotions.net.entities.DetalleAsignatura;

/**
 * Created by gusta on 12/04/2016.
 */
public class DetalleAsignaturaAdapter extends RecyclerView.Adapter<DetalleAsignaturaAdapter.DetalleAsignaturaHolder> {
    List<DetalleAsignatura> asignaturas;

    public DetalleAsignaturaAdapter(List<DetalleAsignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public DetalleAsignaturaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unidad, parent, false);
        DetalleAsignaturaHolder viewHolder = new DetalleAsignaturaHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(DetalleAsignaturaHolder holder, int position) {
        holder.unidad.setText(asignaturas.get(position).getUnidad());
        holder.saber.setText(asignaturas.get(position).getSaber());
        holder.hacer.setText(asignaturas.get(position).getHacer());
        holder.ser.setText(asignaturas.get(position).getSer());
        holder.asistencia.setText(asignaturas.get(position).getAsistencia());
        holder.calFinal.setText(asignaturas.get(position).getCalFinal());
    }

    public int getItemCount() {
        return asignaturas.size();
    }

    public static class DetalleAsignaturaHolder extends RecyclerView.ViewHolder {
        public TextView unidad, saber, ser, hacer, calFinal, asistencia;
        public CardView cardView;

        public DetalleAsignaturaHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.tarjeta_unidad);
            unidad = (TextView) itemView.findViewById(R.id.unidad);
            saber = (TextView) itemView.findViewById(R.id.saber);
            ser = (TextView) itemView.findViewById(R.id.ser);
            hacer = (TextView) itemView.findViewById(R.id.hacer);
            calFinal = (TextView) itemView.findViewById(R.id.finalCal);
            asistencia = (TextView) itemView.findViewById(R.id.asistencia);
        }

    }
}
