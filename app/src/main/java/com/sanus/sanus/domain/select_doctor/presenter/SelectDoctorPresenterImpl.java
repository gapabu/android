package com.sanus.sanus.domain.select_doctor.presenter;


import android.content.Context;

import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractor;
import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractorImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectDoctorPresenterImpl implements SelectDoctorPresenter{
    private SelectDoctorView view;
    private SelectDoctorInteractor interactor;

    public SelectDoctorPresenterImpl(SelectDoctorView view){
        this.view = view;
        interactor = new SelectDoctorInteractorImpl(this);
    }

    @Override
    public void setDataAdapter(List<SelectDoctor> commentsDoctorList) {
        view.setDataAdapter(commentsDoctorList);
    }

    @Override
    public void goSelectDay(String value) {
        view.goSelectDay(value);
    }

    @Override
    public void next() {
        view.next();
    }

    @Override
    public void previous() {
        view.previous();
    }

    @Override
    public void viewDoctor(String idHospital) {
        interactor.viewDoctor(idHospital);
    }

    @Override
    public void buscador(String texto) {
        interactor.buscador(texto);
    }

    @Override
    public void showPhoto(String position, Context context, CircleImageView avatar) {
        interactor.showPhoto(position, context, avatar);
    }
}
