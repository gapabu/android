package com.sanus.sanus.domain.configuration.interactor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;

public class AjustesInteractorImpl implements AjustesInteractor{
    private AjustesPresenter presenter;

    public AjustesInteractorImpl(AjustesPresenter presenter) {
        this.presenter = presenter;
    }

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
