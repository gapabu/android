package com.sanus.sanus.domain.chat.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ChatInteractor {
    void init();
    void showImage(String idImage, final Context context, final CircleImageView image);
}
