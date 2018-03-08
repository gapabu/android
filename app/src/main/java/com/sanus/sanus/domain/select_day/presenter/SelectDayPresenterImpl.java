package com.sanus.sanus.domain.select_day.presenter;


import com.sanus.sanus.domain.select_day.interactor.SelectDayInteractor;
import com.sanus.sanus.domain.select_day.interactor.SelectDayInteractorImpl;
import com.sanus.sanus.domain.select_day.view.SelectDayView;

public class SelectDayPresenterImpl implements SelectDayPresenter{
    private SelectDayView view;
    private SelectDayInteractor interactor;

    public SelectDayPresenterImpl(SelectDayView view){
        this.view = view;
        interactor = new SelectDayInteractorImpl(this);
    }
}
