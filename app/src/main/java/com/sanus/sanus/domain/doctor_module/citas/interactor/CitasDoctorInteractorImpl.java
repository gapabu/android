package com.sanus.sanus.domain.doctor_module.citas.interactor;

import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenter;

public class CitasDoctorInteractorImpl implements CitasDoctorInteractor{
    private CitasDoctorPresenter presenter;
    public CitasDoctorInteractorImpl(CitasDoctorPresenter presenter){this.presenter = presenter;}
}
