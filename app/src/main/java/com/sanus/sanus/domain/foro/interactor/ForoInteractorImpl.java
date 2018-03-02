package com.sanus.sanus.domain.foro.interactor;


import com.sanus.sanus.domain.foro.presenter.ForoPresenter;

public class ForoInteractorImpl implements ForoInteractor{
    private ForoPresenter presenter;

    public ForoInteractorImpl(ForoPresenter presenter) {
        this.presenter = presenter;
    }
}
