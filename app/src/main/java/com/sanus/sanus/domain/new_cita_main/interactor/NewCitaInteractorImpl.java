package com.sanus.sanus.domain.new_cita_main.interactor;

import com.sanus.sanus.domain.new_cita_main.presenter.NewCitaPresenter;

public class NewCitaInteractorImpl implements NewCitaInteractor{
    private NewCitaPresenter presenter;

    public NewCitaInteractorImpl(NewCitaPresenter presenter){this.presenter = presenter;}
}
