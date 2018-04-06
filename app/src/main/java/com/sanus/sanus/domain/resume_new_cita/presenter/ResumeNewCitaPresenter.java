package com.sanus.sanus.domain.resume_new_cita.presenter;


import com.sanus.sanus.domain.resume_new_cita.view.ResumeNewCitaView;

public interface ResumeNewCitaPresenter extends ResumeNewCitaView {
    void addAppointment(String idHospital, String idDoctor, String fecha, String hora);
    void viewDataDoctor(String idDoctor);
}
