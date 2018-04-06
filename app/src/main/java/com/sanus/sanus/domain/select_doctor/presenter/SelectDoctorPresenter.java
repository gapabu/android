package com.sanus.sanus.domain.select_doctor.presenter;


import android.content.Context;

import com.sanus.sanus.domain.select_doctor.view.SelectDoctorView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SelectDoctorPresenter extends SelectDoctorView{
    void viewDoctor(String idHospital);
    void buscador(String texto);
    void showPhoto(String position, Context context, CircleImageView avatar);
}
