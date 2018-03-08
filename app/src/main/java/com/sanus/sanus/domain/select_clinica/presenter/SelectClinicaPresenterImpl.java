package com.sanus.sanus.domain.select_clinica.presenter;

import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractor;
import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractorImpl;
import com.sanus.sanus.domain.select_clinica.view.SelectClinicaView;

public class SelectClinicaPresenterImpl implements SelectClinicaPresenter{
    private SelectClinicaView view;
    private SelectClinicaInteractor interactor;
    public SelectClinicaPresenterImpl(SelectClinicaView view){
        interactor = new SelectClinicaInteractorImpl(this);
    }
}
