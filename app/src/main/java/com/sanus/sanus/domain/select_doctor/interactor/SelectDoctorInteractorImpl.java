package com.sanus.sanus.domain.select_doctor.interactor;

import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;

public class SelectDoctorInteractorImpl implements SelectDoctorInteractor {
    private SelectDoctorPresenter presenter;

    public SelectDoctorInteractorImpl(SelectDoctorPresenter presenter){this.presenter = presenter;}
}
