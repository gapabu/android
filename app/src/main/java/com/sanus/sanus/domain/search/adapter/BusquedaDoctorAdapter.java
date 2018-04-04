package com.sanus.sanus.domain.search.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;
import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.search.presenter.SearchPresenter;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class BusquedaDoctorAdapter extends RecyclerView.Adapter<BusquedaDoctorAdapter.ViewHolder> implements SearchView.OnQueryTextListener {
    private Context context;
    private List<BusquedaDoctor> busquedaDoctorList;
    private SearchPresenter presenter;

    public BusquedaDoctorAdapter(Context context, List<BusquedaDoctor> busquedaDoctorList, SearchPresenter presenter ){
        this.context = context;
        this.busquedaDoctorList = busquedaDoctorList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nombre.setText(busquedaDoctorList.get(position).getNombre());
        holder.especialidad.setText(busquedaDoctorList.get(position).getEspecialidad());

        //presenter.showImage(busquedaDoctorList.get(position).getAvatar(), holder.avatar);
        presenter.showImage(busquedaDoctorList.get(position).getAvatar(), context, holder.avatar);

        if (busquedaDoctorList.get(position).getEstado().equals("1")){
            holder.estado.setVisibility(View.VISIBLE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CurriculumActivity.class);
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

        TextView nombre, especialidad;
        EditText edbuscador;
        CircleImageView avatar;
        ImageView estado;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nombre = itemView.findViewById(R.id.nombre);
            especialidad = itemView.findViewById(R.id.especialidad);
            edbuscador = itemView.findViewById(R.id.edbuscador);
            avatar = itemView.findViewById(R.id.avatar);
            estado = itemView.findViewById(R.id.btnEstado);
        }
    }

}
