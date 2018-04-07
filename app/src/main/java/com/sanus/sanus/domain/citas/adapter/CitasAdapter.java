package com.sanus.sanus.domain.citas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.citas.presenter.CitasPresenter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {
    private Context context;
    private List<Citas> busquedaDoctorList;
    private CitasPresenter presenter;

    public CitasAdapter(Context context, List<Citas> busquedaDoctorList, CitasPresenter presenter) {
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
        this.presenter = presenter;
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
        holder.doctor.setText(busquedaDoctorList.get(position).getDoctor());
        holder.hospital.setText(busquedaDoctorList.get(position).getHospital());

        presenter.showImage(busquedaDoctorList.get(position).getAvatar(), holder.avatar);
    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView fecha, hora, doctor, hospital;
        CircleImageView avatar;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            fecha = itemView.findViewById(R.id.fecha);
            hora = itemView.findViewById(R.id.hora);
            doctor = itemView.findViewById(R.id.doctor);
            hospital = itemView.findViewById(R.id.hospital);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
