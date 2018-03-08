package com.sanus.sanus.domain.new_cita_main.presenter;

import com.sanus.sanus.domain.new_cita_main.interactor.NewCitaInteractor;
import com.sanus.sanus.domain.new_cita_main.interactor.NewCitaInteractorImpl;
import com.sanus.sanus.domain.new_cita_main.view.NewCitaView;

public class NewCitaPresenterImpl implements NewCitaPresenter{
    private NewCitaView view;
    private NewCitaInteractor interactor;

    public NewCitaPresenterImpl(NewCitaView view){
        this.view = view;
        interactor = new NewCitaInteractorImpl(this);
    }
}
