package com.sanus.sanus.domain.login.interactor;

import com.sanus.sanus.domain.login.presenter.LoginPresenter;

public class LoginInteractorImpl implements LoginInteractor {
    private LoginPresenter presenter;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }
}
