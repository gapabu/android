package com.sanus.sanus.domain.select_doctor.presenter;


import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractor;
import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractorImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

import java.util.List;

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
    public void viewComents() {
        interactor.viewComents();
    }

    @Override
    public void buscador(String texto) {
        interactor.buscador(texto);
    }
}
