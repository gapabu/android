package com.sanus.sanus.domain.main.interactor;

import com.sanus.sanus.domain.main.presenter.MainPresenter;

public class MainInteractorImpl implements MainInteractor {
    private MainPresenter presenter;

    public MainInteractorImpl(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
