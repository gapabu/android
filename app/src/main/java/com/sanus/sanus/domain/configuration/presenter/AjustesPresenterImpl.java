package com.sanus.sanus.domain.configuration.presenter;

import com.sanus.sanus.domain.configuration.interactor.AjustesInteractor;
import com.sanus.sanus.domain.configuration.interactor.AjustesInteractorImpl;
import com.sanus.sanus.domain.configuration.view.AjustesView;

public class AjustesPresenterImpl implements AjustesPresenter{
    private AjustesView view;
    private AjustesInteractor interactor;

    public AjustesPresenterImpl(AjustesView view) {
        this.view = view;
        interactor = new AjustesInteractorImpl(this);
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    @Override
    public void goLogin() {
        view.goLogin();
    }
}
