package com.sanus.sanus.domain.select_hour.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

import java.util.List;

public class SelectHourAdapter extends RecyclerView.Adapter<SelectHourAdapter.ViewHolder>  {
    private Context context;
    private List<SelectHour> busquedaDoctorList;
    private SelectHourPresenter presenter;

    public SelectHourAdapter(Context context, List<SelectHour> busquedaDoctorList, SelectHourPresenter presenter ){
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getHorario());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                holder.mView.setBackgroundColor(R.color.colorPrimaryDark);
                presenter.next(busquedaDoctorList.get(position).getHorario());
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
        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nombre = itemView.findViewById(R.id.nombre);

        }
    }

}
