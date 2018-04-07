package com.sanus.sanus.domain.doctor_module.citas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private Context context;
    private List<Appointment> busquedaDoctorList;

    public AppointmentAdapter(Context context, List<Appointment> busquedaDoctorList) {
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.citas_doctor_item, parent, false);
        return new AppointmentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        /*holder.fecha.setText(busquedaDoctorList.get(position).getFecha());
        holder.hora.setText(busquedaDoctorList.get(position).getHora());
        //holder.usuario.setText(busquedaDoctorList.get(position).getUsuario());
        holder.doctor.setText(busquedaDoctorList.get(position).getDoctor());
        holder.hospital.setText(busquedaDoctorList.get(position).getHospital());*/

        holder.fecha.setText(busquedaDoctorList.get(position).getFecha());
        holder.hora.setText(busquedaDoctorList.get(position).getHora());
        holder.usuario.setText(busquedaDoctorList.get(position).getUsuario());

    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        /*TextView fecha, hora, usuario, doctor, hospital;
        CardView cardView;*/
        TextView usuario, fecha, hora;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            /*cardView = itemView.findViewById(R.id.cardView);
            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
            //usuario = itemView.findViewById(R.id.usuario);
            doctor = itemView.findViewById(R.id.doctor);
            hospital = itemView.findViewById(R.id.hospital);*/

            fecha = itemView.findViewById(R.id.fecha);
            usuario = itemView.findViewById(R.id.usuario);
            hora = itemView.findViewById(R.id.hora);
        }
    }
}
