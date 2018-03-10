package com.sanus.sanus.domain.select_clinica.presenter;

import com.sanus.sanus.domain.select_clinica.data.SelectClinica;
import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractor;
import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractorImpl;
import com.sanus.sanus.domain.select_clinica.view.SelectClinicaView;

import java.util.List;

/**
 * Created by Mireya on 10/03/2018.
 */

public class SelectClinicaPresentImpl implements SelectClinicaPresenter {
    private SelectClinicaView view;
    private SelectClinicaInteractor interactor;

    public SelectClinicaPresentImpl(SelectClinicaView view){
        this.view = view;
        interactor = new SelectClinicaInteractorImpl(this);
    }

    @Override
    public void viewClinica() {
        interactor.viewClinica();
    }

    @Override
    public void setDataAdapter(List<SelectClinica> selectClinicaList) {
        view.setDataAdapter(selectClinicaList);
    }
}
