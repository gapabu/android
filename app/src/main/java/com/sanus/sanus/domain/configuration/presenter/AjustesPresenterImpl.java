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
    public void onResume() {
        interactor.onResume();
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    @Override
    public void onClickActive(String estado) {
        interactor.onClickActive(estado);
    }


    @Override
    public void goSplash() {
        view.goSplash();
    }

    @Override
    public void showName(String name) {
        view.showName(name);
    }

    @Override
    public void showData(String name, String email) {
        view.showData(name, email);
    }

    @Override
    public void showPhoto(String photo) {
        view.showPhoto(photo);
    }

    @Override
    public void selectActive() {
        view.selectActive();
    }

    @Override
    public void selectInactive() {
        view.selectInactive();
    }

}
