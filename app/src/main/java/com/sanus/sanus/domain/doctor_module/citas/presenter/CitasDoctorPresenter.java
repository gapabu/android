package com.sanus.sanus.domain.doctor_module.citas.presenter;

import com.sanus.sanus.domain.doctor_module.citas.view.CitasDoctorFragmentView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CitasDoctorPresenter extends CitasDoctorFragmentView{
    void init();
    void showImage(String idImage, CircleImageView image);
}
