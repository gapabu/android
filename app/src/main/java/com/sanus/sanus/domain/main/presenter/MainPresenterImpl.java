package com.sanus.sanus.domain.main.presenter;

import com.sanus.sanus.domain.main.interactor.MainInteractor;
import com.sanus.sanus.domain.main.interactor.MainInteractorImpl;
import com.sanus.sanus.domain.main.view.MainView;

public class MainPresenterImpl implements MainPresenter {
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl(this);
    }
}
