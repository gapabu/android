package com.sanus.sanus.domain.select_day.interactor;


import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;

public class SelectDayInteractorImpl implements SelectDayInteractor{
    private SelectDayPresenter presenter;

    public SelectDayInteractorImpl(SelectDayPresenter presenter){this.presenter = presenter;}
}
