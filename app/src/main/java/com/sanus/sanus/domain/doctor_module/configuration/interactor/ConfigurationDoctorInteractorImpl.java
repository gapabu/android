package com.sanus.sanus.domain.doctor_module.configuration.interactor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sanus.sanus.domain.doctor_module.configuration.presenter.ConfigurationDoctorPresenter;

public class ConfigurationDoctorInteractorImpl implements ConfigurationDoctorInteractor {
    private ConfigurationDoctorPresenter presenter;

    public ConfigurationDoctorInteractorImpl(ConfigurationDoctorPresenter presenter){this.presenter = presenter;}

    @Override
    public void onResume() {
        showAccount();
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        presenter.goSplash();
    }

    private void showAccount(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            presenter.showData(user.getDisplayName(), user.getEmail());

            if (user.getPhotoUrl()!=null) {
                presenter.showPhoto(user.getPhotoUrl().toString());
            }
        }else{
            presenter.showData("Nombre", "Correo");
        }
    }
}

