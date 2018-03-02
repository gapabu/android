package com.sanus.sanus.domain.configuration.interactor;

import com.google.firebase.auth.FirebaseAuth;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;

public class AjustesInteractorImpl implements AjustesInteractor{
    private AjustesPresenter presenter;

    public AjustesInteractorImpl(AjustesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        presenter.goLogin();
    }
}
