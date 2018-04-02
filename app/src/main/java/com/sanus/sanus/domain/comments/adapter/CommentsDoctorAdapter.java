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
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsDoctorAdapter extends RecyclerView.Adapter<CommentsDoctorAdapter.ViewHolder>{
    private Context context;
    private List<CommentsDoctor> commentsDoctorList;
    private CommentsPresenter presenter;

    public CommentsDoctorAdapter(Context context, List<CommentsDoctor> commentsDoctorList, CommentsPresenter presenter){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public CommentsDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item, parent, false);
        return new CommentsDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsDoctorAdapter.ViewHolder holder, int position) {
        holder.comentario.setText(commentsDoctorList.get(position).getCometario());
        holder.usuario.setText(commentsDoctorList.get(position).getUsuario());
        holder.fecha.setText(commentsDoctorList.get(position).getFecha());
        holder.calificacion.setRating(Float.parseFloat(commentsDoctorList.get(position).getCalificacion())/20);

        presenter.showImage(commentsDoctorList.get(position).getAvatar(), holder.avatar);
    }

    @Override
    public int getItemCount() {
        return commentsDoctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView fecha, usuario, comentario;
        RatingBar calificacion;
        CircleImageView avatar;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            fecha =  itemView.findViewById(R.id.tvFecha);
            usuario =  itemView.findViewById(R.id.tvUsuario);
            comentario =  itemView.findViewById(R.id.tvComentario);
            calificacion =  itemView.findViewById(R.id.ratingBarVal);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
