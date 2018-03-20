package com.sanus.sanus.domain.doctor_module.main_doctor.interactor;

import com.sanus.sanus.domain.doctor_module.main_doctor.presenter.MainDoctorPresenter;

public class MainDoctorInteractorImpl implements MainDoctorInteractor {
    private MainDoctorPresenter presenter;

    public MainDoctorInteractorImpl(MainDoctorPresenter presenter){this.presenter = presenter;}
}
