package com.sanus.sanus.domain.comments.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CommentsInteractor {
    void viewComents(String idDoc);
    void showImage(String idImage, final Context context, final CircleImageView image);
}
