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

    @Override
    public void onResume() {
        interactor.onResume();
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    @Override
    public void goSplash() {
        view.goSplash();
    }

    @Override
    public void showData(String name, String email) {
        view.showData(name, email);
    }

    @Override
    public void showPhoto(String photo) {
        view.showPhoto(photo);
    }
}
