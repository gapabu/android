package com.sanus.sanus.domain.doctor_module.all_comments.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface AllCommentsInteractor {
    void viewComents(String idDoc);
    void showImage(String idImage, final Context context, final CircleImageView image);
}
