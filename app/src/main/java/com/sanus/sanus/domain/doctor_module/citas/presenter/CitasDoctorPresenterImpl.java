package com.sanus.sanus.domain.doctor_module.citas.presenter;

import android.net.Uri;

import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;
import com.sanus.sanus.domain.doctor_module.citas.interactor.CitasDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.citas.interactor.CitasDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.citas.view.CitasDoctorFragmentView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitasDoctorPresenterImpl implements CitasDoctorPresenter{
    private CitasDoctorFragmentView view;
    private CitasDoctorInteractor interactor;

    public CitasDoctorPresenterImpl(CitasDoctorFragmentView view){
        this.view = view;
        interactor = new CitasDoctorInteractorImpl(this);
    }

    @Override
    public void setDataAdapter(List<Appointment> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }

    @Override
    public void showPhoto(Uri photo, CircleImageView avatar) {
        view.showPhoto(photo, avatar);
    }

    @Override
    public void init() {
        interactor.init();
    }

    @Override
    public void showImage(String idImage,CircleImageView image) {
        interactor.showImage(idImage, image);
    }



}
