package com.sanus.sanus.domain.doctor_module.configuration.interactor;

import com.sanus.sanus.domain.doctor_module.configuration.presenter.ConfigurationDoctorPresenter;

public class ConfigurationDoctorInteractorImpl implements ConfigurationDoctorInteractor {
    private ConfigurationDoctorPresenter presenter;

    public ConfigurationDoctorInteractorImpl(ConfigurationDoctorPresenter presenter){this.presenter = presenter;}
}
