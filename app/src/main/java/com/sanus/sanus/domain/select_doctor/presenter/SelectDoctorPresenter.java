package com.sanus.sanus.domain.select_doctor.presenter;


import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

public interface SelectDoctorPresenter extends SelectDoctorView{
    void viewComents();
    void buscador(String texto);
}
