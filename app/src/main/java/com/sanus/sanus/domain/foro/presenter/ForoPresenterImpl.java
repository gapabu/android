package com.sanus.sanus.domain.foro.presenter;


import com.sanus.sanus.domain.foro.interactor.ForoInteractor;
import com.sanus.sanus.domain.foro.interactor.ForoInteractorImpl;
import com.sanus.sanus.domain.foro.view.ForoView;

public class ForoPresenterImpl implements ForoPresenter{
    private ForoView view;
    private ForoInteractor interactor;

    public ForoPresenterImpl(ForoView view) {
        this.view = view;
        interactor = new ForoInteractorImpl(this);
    }
}
