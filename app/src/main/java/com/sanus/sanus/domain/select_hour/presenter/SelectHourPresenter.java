package com.sanus.sanus.domain.select_hour.presenter;


import com.sanus.sanus.domain.select_hour.view.SelectHourView;

public interface SelectHourPresenter extends SelectHourView {
    void viewSchedules(String idDoctor, String dia);
    void addAppointment(String idHospital, String idDoctor, String fecha, String hora, String idDocument);
}
