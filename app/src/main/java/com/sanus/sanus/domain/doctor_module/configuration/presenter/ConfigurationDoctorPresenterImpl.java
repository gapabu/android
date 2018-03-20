package com.sanus.sanus.domain.doctor_module.configuration.presenter;

import com.sanus.sanus.domain.doctor_module.configuration.interactor.ConfigurationDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.configuration.interactor.ConfigurationDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.configuration.view.ConfigurationDoctorFragmentView;

public class ConfigurationDoctorPresenterImpl implements ConfigurationDoctorPresenter {
    private ConfigurationDoctorFragmentView view;
    private ConfigurationDoctorInteractor interactor;

    public ConfigurationDoctorPresenterImpl(ConfigurationDoctorFragmentView view){
        this.view = view;
        interactor = new ConfigurationDoctorInteractorImpl(this);
    }
}
