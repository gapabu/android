package com.sanus.sanus.domain.select_hour.presenter;


import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.interactor.SelectHourInteractor;
import com.sanus.sanus.domain.select_hour.interactor.SelectHourInteractorImpl;
import com.sanus.sanus.domain.select_hour.view.SelectHourView;

import java.util.List;

public class SelectHourPresenterImpl implements SelectHourPresenter {
    private SelectHourView view;
    private SelectHourInteractor interactor;

    public SelectHourPresenterImpl(SelectHourView view){
        this.view = view;
        interactor = new SelectHourInteractorImpl(this);
    }

    @Override
    public void viewSchedules(String idDoctor, String dia) {
        interactor.viewSchedules(idDoctor, dia);
    }

    @Override
    public void addAppointment(String idHospital, String idDoctor, String fecha, String hora, String idDocument) {
        interactor.addAppointment(idHospital, idDoctor, fecha, hora, idDocument);
    }

    @Override
    public void deleteAppointment(String idDocument) {
        interactor.deleteAppointment(idDocument);
    }

    @Override
    public void addDataCite(String idDoctor, String  fecha, String hora) {
        interactor.addDataCite(idDoctor, fecha, hora);
    }

    @Override
    public void enableButton() {
        view.enableButton();
    }

    @Override
    public void disableButton() {
        view.disableButton();
    }

    @Override
    public void next(String value) {
        view.next(value);
    }

    @Override
    public void previous() {
        view.previous();
    }

    @Override
    public void setDataAdapter(List<SelectHour> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }

    @Override
    public void setDate(String idHora, String idFecha) {
        view.setDate(idHora, idFecha);
    }

}
