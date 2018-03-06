package com.sanus.sanus.domain.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.R;

import java.util.ArrayList;
import java.util.List;

public class BusquedaDoctorAdapter extends RecyclerView.Adapter<BusquedaDoctorAdapter.ViewHolder> implements SearchView.OnQueryTextListener {
    private Context context;
    private List<BusquedaDoctor> busquedaDoctorList;

    public BusquedaDoctorAdapter(Context context, List<BusquedaDoctor> busquedaDoctorList){
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getNombre());
        holder.especialidad.setText(busquedaDoctorList.get(position).getEspecialidad());


        final String user_id = busquedaDoctorList.get(position).userId;


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "User ID: " + user_id, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, CurriculumActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //https://www.youtube.com/watch?v=ZXoGG2XTjzU
            }


        });
    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    public void setFilter(List<BusquedaDoctor> busquedaDoctorList){
        busquedaDoctorList = new ArrayList<>();
        busquedaDoctorList.addAll(busquedaDoctorList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        TextView nombre, especialidad;
        CardView cardView;
        ImageView imageView;
        EditText edbuscador;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            //cardView = itemView.findViewById(R.id.cardView);
            nombre = itemView.findViewById(R.id.nombre);
            especialidad = itemView.findViewById(R.id.especialidad);
            imageView = itemView.findViewById(R.id.avatar);

            //buscador
            edbuscador = itemView.findViewById(R.id.edbuscador);

        }
    }

}
