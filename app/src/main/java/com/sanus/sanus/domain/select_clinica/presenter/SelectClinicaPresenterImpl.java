package com.sanus.sanus.domain.select_clinica.presenter;

import com.sanus.sanus.domain.select_clinica.data.ClinicaData;
import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractor;
import com.sanus.sanus.domain.select_clinica.interactor.SelectClinicaInteractorImpl;
import com.sanus.sanus.domain.select_clinica.view.SelectClinicaView;

import java.util.List;

public class SelectClinicaPresenterImpl implements SelectClinicaPresenter{
    private SelectClinicaView view;
    private SelectClinicaInteractor interactor;

    public SelectClinicaPresenterImpl(SelectClinicaView view){
        this.view = view;
        interactor = new SelectClinicaInteractorImpl(this);

    }


    @Override
    public void setDataAdapter(List<ClinicaData> clinicaDataList) {
        view.setDataAdapter(clinicaDataList);
    }

    @Override
    public void viewClinic() {
        interactor.viewClinic();
    }
}
