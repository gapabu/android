package com.sanus.sanus.domain.doctor_module.chat.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ChatDoctorInteractor {
    void init();
    void showImage(String idImage, final Context context, final CircleImageView image);
}
