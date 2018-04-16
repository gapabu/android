package com.sanus.sanus.domain.select_day.interactor;


public interface SelectDayInteractor {
    void addAppointment(String idHospital, String idDoctor, String fecha);
    void deleteAppointment(String idDocument);
}
