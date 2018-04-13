package com.sanus.sanus.domain.select_day.presenter;

import com.sanus.sanus.domain.select_day.view.SelectDayView;

public interface SelectDayPresenter extends SelectDayView {
    void addAppointment(String idHospital, String idDoctor, String fecha);
}
