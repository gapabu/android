package com.sanus.sanus.domain.resume_new_cita.interactor;

public interface ResumeNewCitaInteractor {
    void addAppointment(String idHospital, String idDoctor, String fecha, String hora, String idDocument);
    void viewDataDoctor(String idDoctor);
    void viewDataHospital(String idHospital);
    void deleteAppointment(String idDocument);
    void showImage(String idImage);
}
