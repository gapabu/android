package com.sanus.sanus.domain.doctor_module.chat.presenter;

import android.content.Context;

import com.sanus.sanus.domain.doctor_module.chat.view.ChatDoctorFragmentView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ChatDoctorPresent extends ChatDoctorFragmentView{
    void init();
    void showImage(String idImage, final Context context, final CircleImageView image);
}
