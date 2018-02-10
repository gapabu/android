package com.sanus.sanus.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.Data.BusquedaDoctor;
import com.sanus.sanus.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mireya on 09/02/2018.
 */

public class BusquedaDoctorAdapter extends RecyclerView.Adapter<BusquedaDoctorAdapter.ViewHolder> {
    Context context;
    List<BusquedaDoctor> busquedaDoctorList;


    public BusquedaDoctorAdapter(Context context, List<BusquedaDoctor> busquedaDoctorList){
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_item, parent, false);
        //ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getNombre());
        holder.especialidad.setText(busquedaDoctorList.get(position).getEspecialidad());

    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        TextView nombre, especialidad;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);
            mView = itemView;

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            especialidad = (TextView) itemView.findViewById(R.id.especialidad);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }


}
