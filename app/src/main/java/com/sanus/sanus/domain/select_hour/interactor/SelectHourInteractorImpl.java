package com.sanus.sanus.domain.select_hour.interactor;


import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

public class SelectHourInteractorImpl implements SelectHourInteractor {
    private SelectHourPresenter presenter;

    public SelectHourInteractorImpl(SelectHourPresenter presenter){this.presenter = presenter;}
}
