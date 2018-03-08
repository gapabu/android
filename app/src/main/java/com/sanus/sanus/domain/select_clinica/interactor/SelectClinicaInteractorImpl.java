package com.sanus.sanus.domain.select_clinica.interactor;


import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;

public class SelectClinicaInteractorImpl implements SelectClinicaInteractor{
    private SelectClinicaPresenter presenter;

    public SelectClinicaInteractorImpl(SelectClinicaPresenter presenter){this.presenter = presenter;}
}
