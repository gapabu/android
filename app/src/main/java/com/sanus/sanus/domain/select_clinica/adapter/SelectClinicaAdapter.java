package com.sanus.sanus.domain.select_clinica.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_clinica.data.ClinicaData;

import java.util.List;

public class SelectClinicaAdapter extends RecyclerView.Adapter<SelectClinicaAdapter.ViewHolder> {
 Context context;
 List<ClinicaData> clinicaDataList;

public SelectClinicaAdapter(Context context, List<ClinicaData> clinicaDataList){
    this.context = context;
    this.clinicaDataList = clinicaDataList;
}
    @NonNull
    @Override
    public SelectClinicaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinicas_item, parent, false);
        return new SelectClinicaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectClinicaAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(clinicaDataList.get(position).getNombre());
        holder.direccion.setText(clinicaDataList.get(position).getDireccion());

    }

    @Override
    public int getItemCount() {
        return clinicaDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView nombre, direccion;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            nombre = itemView.findViewById(R.id.clinica);
            direccion = itemView.findViewById(R.id.direccion);
        }
    }
}
