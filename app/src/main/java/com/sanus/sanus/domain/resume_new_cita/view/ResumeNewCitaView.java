package com.sanus.sanus.domain.resume_new_cita.view;

public interface ResumeNewCitaView {
    String setDate();
    String setHour();
    String setNameDoctor(String nameDr);
    String setSpecialty(String especialidad);
    String setNameHospital(String nameHos);
    String setDirectionHospital(String direction);

    void showExitoRegistro();
    void goActivity();

}
