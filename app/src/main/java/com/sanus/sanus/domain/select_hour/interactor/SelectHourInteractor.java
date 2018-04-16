package com.sanus.sanus.domain.select_hour.interactor;

public interface SelectHourInteractor {
    void viewSchedules(String idDoctor, String dia);
    void addAppointment(String hospital, String idHospital, String idDoctor, String fecha, String hora);
    void deleteAppointment(String idDocument);
}
