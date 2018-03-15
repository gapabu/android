package com.sanus.sanus.domain.select_doctor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

        /*final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        });*/
        String url= "https://firebasestorage.googleapis.com/v0/b/sanus-27.appspot.com/o/avatar%2FbOCiPsUoNMMfh09ddh9M5hQOn163?alt=media&token=03d6b45d-5f32-499e-9827-02ace8dcabb6";
        Picasso.with(context).load(url).placeholder(R.drawable.default_image).into(holder.avatar);
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
        CircleImageView avatar;


        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            nombre =  itemView.findViewById(R.id.nombre);
            especialidad =  itemView.findViewById(R.id.especialidad);
            //buscador
            edbuscador = itemView.findViewById(R.id.edbuscador);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}