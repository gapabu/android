package com.sanus.sanus.domain.chat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.chat.data.ContactUser;
import com.sanus.sanus.domain.chat.presenter.ChatPresenter;
import com.sanus.sanus.domain.new_chat.view.NewChatActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactUserAdapter extends RecyclerView.Adapter<ContactUserAdapter.ViewHolder>  {
    private Context context;
    private List<ContactUser> busquedaDoctorList;
    private ChatPresenter presenter;

    public ContactUserAdapter(Context context, List<ContactUser> busquedaDoctorList, ChatPresenter presenter ){
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getNombre());

        presenter.showImage(busquedaDoctorList.get(position).getAvatar(),context, holder.avatar);
        if (busquedaDoctorList.get(position).getEstado().equals("1")){
            holder.estado.setVisibility(View.VISIBLE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewChatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idDoctor", busquedaDoctorList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busquedaDoctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        TextView nombre;
        CircleImageView avatar;
        FloatingActionButton estado;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nombre = itemView.findViewById(R.id.nombre);
            avatar = itemView.findViewById(R.id.avatar);
            estado = itemView.findViewById(R.id.btnEstado);
        }
    }

}