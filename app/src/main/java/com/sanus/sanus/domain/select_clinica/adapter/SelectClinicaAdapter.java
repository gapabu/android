package com.sanus.sanus.domain.select_clinica.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;

import java.util.List;

public class SelectClinicaAdapter extends RecyclerView.Adapter<SelectClinicaAdapter.ViewHolder> {
private Context context;

public SelectClinicaAdapter(Context context){
    this.context = context;
}
    @NonNull
    @Override
    public SelectClinicaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinicas_item, parent, false);
        return new SelectClinicaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectClinicaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
