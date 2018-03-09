package com.sanus.sanus.domain.select_hour.presenter;


import com.sanus.sanus.domain.select_hour.interactor.SelectHourInteractor;
import com.sanus.sanus.domain.select_hour.interactor.SelectHourInteractorImpl;
import com.sanus.sanus.domain.select_hour.view.SelectHourView;

public class SelectHourPresenterImpl implements SelectHourPresenter {
    private SelectHourView view;
    private SelectHourInteractor interactor;

    public SelectHourPresenterImpl(SelectHourView view){
        this.view = view;
        interactor = new SelectHourInteractorImpl(this);
    }
}