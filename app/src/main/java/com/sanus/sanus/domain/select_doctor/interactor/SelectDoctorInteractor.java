package com.sanus.sanus.domain.select_doctor.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SelectDoctorInteractor {
    void viewDoctor(String idHospital);
    void buscador(String texto);
    void showPhoto(String position, Context context, CircleImageView avatar);
}
