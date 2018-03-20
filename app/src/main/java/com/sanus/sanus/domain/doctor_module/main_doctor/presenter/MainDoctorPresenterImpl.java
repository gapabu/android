package com.sanus.sanus.domain.doctor_module.main_doctor.presenter;

import com.sanus.sanus.domain.doctor_module.main_doctor.interactor.MainDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.main_doctor.interactor.MainDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.main_doctor.view.MainDoctorView;

public class MainDoctorPresenterImpl implements MainDoctorPresenter{
    private MainDoctorView view;
    private MainDoctorInteractor interactor;

    public MainDoctorPresenterImpl(MainDoctorView view){
        this.view = view;
        interactor = new MainDoctorInteractorImpl(this);
    }
}
