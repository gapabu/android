package com.sanus.sanus.domain.login.presenter;

import com.sanus.sanus.domain.login.interactor.LoginInteractor;
import com.sanus.sanus.domain.login.interactor.LoginInteractorImpl;
import com.sanus.sanus.domain.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor= new LoginInteractorImpl(this);
    }

}
