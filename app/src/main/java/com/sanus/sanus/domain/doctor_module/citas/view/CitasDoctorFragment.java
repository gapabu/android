package com.sanus.sanus.domain.doctor_module.citas.view;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanus.sanus.R;
import com.sanus.sanus.domain.doctor_module.citas.adapter.AppointmentAdapter;
import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;
import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenter;
import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenterImpl;
import com.sanus.sanus.utils.glide.GlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitasDoctorFragment extends Fragment implements CitasDoctorFragmentView {

    public static String IDENTIFIER = "CITAS_DOCTOR_FRAGMENT";
    private CitasDoctorPresenter presenter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citas_doctor, container, false);
        setUpVariable();
        setUpView(view);
        presenter.init();
        return view;
    }

    private void setUpVariable() {
        if (presenter == null){
            presenter = new CitasDoctorPresenterImpl(this);
        }
    }

    private void setUpView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setDataAdapter(List<Appointment> busquedaDoctors) {
        AppointmentAdapter citasAdapter = new AppointmentAdapter(getContext(), busquedaDoctors, presenter);
        recyclerView.setAdapter(citasAdapter);
        citasAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPhoto(Uri uri, CircleImageView avatar) {
        GlideApp.with(this).load(uri.toString()).placeholder(R.drawable.user).into(avatar);
    }
}
