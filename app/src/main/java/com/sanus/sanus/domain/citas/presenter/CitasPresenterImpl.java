package com.sanus.sanus.domain.citas.presenter;

import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.domain.citas.interactor.CitasInteractor;
import com.sanus.sanus.domain.citas.interactor.CitasInteractorImpl;
import com.sanus.sanus.domain.citas.view.CitasView;

import java.util.List;

public class CitasPresenterImpl implements  CitasPresenter {
    private CitasView view;
    private CitasInteractor interactor;

    public CitasPresenterImpl(CitasView view) {
        this.view = view;
        interactor = new CitasInteractorImpl(this);

    }

    @Override
    public void init() {
        interactor.init();
    }

    @Override
    public void setDataAdapter(List<Citas> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }
}
