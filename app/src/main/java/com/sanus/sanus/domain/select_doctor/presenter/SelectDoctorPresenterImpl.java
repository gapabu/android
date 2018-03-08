package com.sanus.sanus.domain.select_doctor.presenter;


import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractor;
import com.sanus.sanus.domain.select_doctor.interactor.SelectDoctorInteractorImpl;
import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

public class SelectDoctorPresenterImpl implements SelectDoctorPresenter{
    private SelectDoctorView view;
    private SelectDoctorInteractor interactor;

    public SelectDoctorPresenterImpl(SelectDoctorView view){
        this.view = view;
        interactor = new SelectDoctorInteractorImpl(this);
    }
}
