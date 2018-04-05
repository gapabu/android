package com.sanus.sanus.domain.select_doctor.presenter;


import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

public interface SelectDoctorPresenter extends SelectDoctorView{
    void viewDoctor(String idHospital);
    void buscador(String texto);
}
