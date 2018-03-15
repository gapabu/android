package com.sanus.sanus.domain.select_doctor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;

import java.util.ArrayList;
import java.util.List;

public class SelectDoctorAdapter extends RecyclerView.Adapter<SelectDoctorAdapter.ViewHolder> implements SearchView.OnQueryTextListener{
    private Context context;
    private List<SelectDoctor> commentsDoctorList;

    public SelectDoctorAdapter(Context context, List<SelectDoctor> commentsDoctorList){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
    }
    @NonNull
    @Override
    public SelectDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new SelectDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectDoctorAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(commentsDoctorList.get(position).getNombre());
        holder.especialidad.setText(commentsDoctorList.get(position).getEspecialidad());
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

    public void setFilter(List<SelectDoctor> commentsDoctorList){
        commentsDoctorList = new ArrayList<>();
        commentsDoctorList.addAll(commentsDoctorList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView nombre,especialidad;
        EditText edbuscador;
        LinearLayout datos;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            nombre =  itemView.findViewById(R.id.nombre);
            especialidad =  itemView.findViewById(R.id.especialidad);
            //buscador
            edbuscador = itemView.findViewById(R.id.edbuscador);
        }
    }
}