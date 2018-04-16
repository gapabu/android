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

    @Override
    public void enableButton() {
        view.enableButton();
    }

    @Override
    public void disableButton() {
        view.disableButton();
    }

    @Override
    public void next(String idDocument) {
        view.next(idDocument);
    }

    @Override
    public void previous() {
        view.previous();
    }

    @Override
    public void addAppointment(String idHospital, String idDoctor, String fecha) {
        interactor.addAppointment(idHospital, idDoctor, fecha);
    }

    @Override
    public void deleteAppointment(String idDocument) {
        interactor.deleteAppointment(idDocument);
    }
}
