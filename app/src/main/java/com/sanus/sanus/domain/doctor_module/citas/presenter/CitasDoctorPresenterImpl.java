package com.sanus.sanus.domain.doctor_module.citas.presenter;

import com.sanus.sanus.domain.doctor_module.citas.interactor.CitasDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.citas.interactor.CitasDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.citas.view.CitasDoctorFragmentView;

public class CitasDoctorPresenterImpl implements CitasDoctorPresenter{
    private CitasDoctorFragmentView view;
    private CitasDoctorInteractor interactor;

    public CitasDoctorPresenterImpl(CitasDoctorFragmentView view){
        this.view = view;
        interactor = new CitasDoctorInteractorImpl(this);
    }
}
