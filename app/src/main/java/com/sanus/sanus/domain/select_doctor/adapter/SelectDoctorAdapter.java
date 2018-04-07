package com.sanus.sanus.domain.select_doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectDoctorAdapter extends RecyclerView.Adapter<SelectDoctorAdapter.ViewHolder> implements SearchView.OnQueryTextListener{
    private Context context;
    private List<SelectDoctor> commentsDoctorList;
    private SelectDoctorPresenter presenter;

    public SelectDoctorAdapter(Context context, List<SelectDoctor> commentsDoctorList, SelectDoctorPresenter presenter){
        this.context = context;
        this.commentsDoctorList = commentsDoctorList;
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public SelectDoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new SelectDoctorAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final SelectDoctorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nombre.setText(commentsDoctorList.get(position).getNombre());
        holder.especialidad.setText(commentsDoctorList.get(position).getEspecialidad());

        presenter.showPhoto(commentsDoctorList.get(position).getAvatar(), context, holder.avatar);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mView.setBackgroundColor(R.color.colorPrimaryDark);
                presenter.goSelectDay(commentsDoctorList.get(position).getId());
            }
        });
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
            edbuscador = itemView.findViewById(R.id.edbuscador);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}