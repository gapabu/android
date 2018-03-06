package com.sanus.sanus.domain.comments.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.data.CommentsDoctor;

import java.util.List;



public class CommentsDoctorAdapter extends RecyclerView.Adapter<CommentsDoctorAdapter.ViewHolder>{
    Context context;
    List<CommentsDoctor> commentsDoctorList;

    public CommentsDoctorAdapter(Context context, List<CommentsDoctor> commentsDoctorList){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
    }
    @NonNull
    @Override
    public CommentsDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item, parent, false);
        return new CommentsDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsDoctorAdapter.ViewHolder holder, int position) {
        holder.comentario.setText(commentsDoctorList.get(position).getCometario());
        holder.usuario.setText(commentsDoctorList.get(position).getUsuario());
        holder.fecha.setText(commentsDoctorList.get(position).getFecha());
        holder.calificacion.setRating(3);
    }

    @Override
    public int getItemCount() {
        return commentsDoctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView fecha, usuario, comentario;
        RatingBar calificacion;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            fecha =  itemView.findViewById(R.id.tvFecha);
            usuario =  itemView.findViewById(R.id.tvUsuario);
            comentario =  itemView.findViewById(R.id.tvComentario);
            calificacion =  itemView.findViewById(R.id.ratingBarVal);
        }
    }
}
