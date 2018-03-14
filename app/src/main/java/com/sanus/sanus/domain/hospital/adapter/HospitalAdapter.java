package com.sanus.sanus.domain.hospital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.hospital.data.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> implements SearchView.OnQueryTextListener{
    private Context context;
    private List<Hospital> commentsDoctorList;

    public HospitalAdapter(Context context, List<Hospital> commentsDoctorList){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
    }
    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item, parent, false);
        return new HospitalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(commentsDoctorList.get(position).getNombre());
        holder.direccion.setText(commentsDoctorList.get(position).getDireccion());

    }

    @Override
    public int getItemCount() {
        return commentsDoctorList.size();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void setFilter(List<Hospital> commentsDoctorList){
        commentsDoctorList = new ArrayList<>();
        commentsDoctorList.addAll(commentsDoctorList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView nombre,direccion;
        EditText edbuscador;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            nombre =  itemView.findViewById(R.id.nameHospital);
            direccion =  itemView.findViewById(R.id.direccionHospital);
            //buscador
            edbuscador = itemView.findViewById(R.id.edbuscador);
        }
    }
}