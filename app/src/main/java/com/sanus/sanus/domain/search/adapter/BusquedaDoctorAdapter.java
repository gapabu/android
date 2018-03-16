package com.sanus.sanus.domain.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getNombre());
        holder.especialidad.setText(busquedaDoctorList.get(position).getEspecialidad());

        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(busquedaDoctorList.get(position).getAvatar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri.toString()).placeholder(R.drawable.user).into(holder.avatar);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Error al traer foto", Toast.LENGTH_SHORT).show();
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CurriculumActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", busquedaDoctorList.get(position).getId());
                context.startActivity(intent);
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
        EditText edbuscador;
        CircleImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nombre = itemView.findViewById(R.id.nombre);
            especialidad = itemView.findViewById(R.id.especialidad);
            edbuscador = itemView.findViewById(R.id.edbuscador);
            avatar = itemView.findViewById(R.id.avatar);

        }
    }

}
