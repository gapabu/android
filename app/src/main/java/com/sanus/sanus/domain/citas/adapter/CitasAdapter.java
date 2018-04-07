package com.sanus.sanus.domain.citas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.R;

import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {
    private Context context;
    private List<Citas> busquedaDoctorList;

    public CitasAdapter(Context context, List<Citas> busquedaDoctorList) {
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
    }

    @NonNull
    @Override
    public CitasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.citas_item, parent, false);
        return new CitasAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CitasAdapter.ViewHolder holder, int position) {
        holder.fecha.setText(busquedaDoctorList.get(position).getFecha());
        holder.hora.setText(busquedaDoctorList.get(position).getHora());
        //holder.usuario.setText(busquedaDoctorList.get(position).getUsuario());
        holder.doctor.setText(busquedaDoctorList.get(position).getDoctor());
        holder.hospital.setText(busquedaDoctorList.get(position).getHospital());
    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView fecha, hora, usuario, doctor, hospital;
        CardView cardView;


        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            cardView = itemView.findViewById(R.id.cardView);
            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
            //usuario = itemView.findViewById(R.id.usuario);
            doctor = itemView.findViewById(R.id.doctor);
            hospital = itemView.findViewById(R.id.hospital);
        }
    }
}
