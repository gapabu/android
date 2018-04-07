package com.sanus.sanus.domain.doctor_module.citas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;
import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private Context context;
    private List<Appointment> busquedaDoctorList;
    private CitasDoctorPresenter presenter;

    public AppointmentAdapter(Context context, List<Appointment> busquedaDoctorList, CitasDoctorPresenter presenter) {
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.citas_doctor_item, parent, false);
        return new AppointmentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        holder.fecha.setText(busquedaDoctorList.get(position).getFecha());
        holder.hora.setText(busquedaDoctorList.get(position).getHora());
        holder.usuario.setText(busquedaDoctorList.get(position).getUsuario());
        presenter.showImage(busquedaDoctorList.get(position).getAvatar(), holder.avatar);

    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView usuario, fecha, hora;
        CircleImageView avatar;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            fecha = itemView.findViewById(R.id.fecha);
            usuario = itemView.findViewById(R.id.usuario);
            hora = itemView.findViewById(R.id.hora);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
