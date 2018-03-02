package com.sanus.sanus.domain.splash.presenter;


import com.sanus.sanus.domain.splash.interactor.SplashInteractor;
import com.sanus.sanus.domain.splash.interactor.SplashInteractorImpl;
import com.sanus.sanus.domain.splash.view.SplasView;

public class SplashPresenterImpl implements SplashPresenter {
    private SplasView view;
    private SplashInteractor interactor;

    public SplashPresenterImpl(SplasView view) {
        this.view = view;
        interactor = new SplashInteractorImpl(this);
    }

    @Override
    public void init() {
        interactor.init();
    }

    @Override
    public void goLogin() {
        view.goLogin();
    }
}
