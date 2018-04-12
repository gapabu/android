package com.sanus.sanus.domain.select_hour.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

import java.util.List;

public class SelectHourAdapter extends RecyclerView.Adapter<SelectHourAdapter.ViewHolder>{
    private Context context;
    private List<SelectHour> commentsDoctorList;
    private SelectHourPresenter presenter;

    public SelectHourAdapter(Context context, List<SelectHour> commentsDoctorList, SelectHourPresenter presenter){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public SelectHourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_item, parent, false);
        return new SelectHourAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectHourAdapter.ViewHolder holder, int position) {
        //holder.comentario.setText(commentsDoctorList.get(position).getCometario());
        holder.horario.setText(commentsDoctorList.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return commentsDoctorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView horario;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            horario = itemView.findViewById(R.id.horario);
        }
    }
}


