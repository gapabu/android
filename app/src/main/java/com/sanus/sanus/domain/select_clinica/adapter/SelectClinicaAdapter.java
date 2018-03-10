package com.sanus.sanus.domain.select_clinica.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_clinica.data.SelectClinica;

import java.util.List;

/**
 * Created by Mireya on 10/03/2018.
 */

public class SelectClinicaAdapter extends RecyclerView.Adapter<SelectClinicaAdapter.ViewHolder>{
   private Context context;
   private List<SelectClinica> selectClinicaList;

   public SelectClinicaAdapter(Context context, List<SelectClinica> selectClinicaList){
       this.context = context;
       this.selectClinicaList = selectClinicaList;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinicas_item, parent, false);
        return new SelectClinicaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.nombre.setText(selectClinicaList.get(position).getNombre());
       holder.direccion.setText(selectClinicaList.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return selectClinicaList.size();
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
