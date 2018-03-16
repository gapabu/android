package com.sanus.sanus.domain.select_doctor.presenter;


import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

public interface SelectDoctorPresenter extends SelectDoctorView{
    void viewComents(String idDoc);
    void buscador(String texto);
}
