package com.sanus.sanus.domain.comments.presenter;

import android.content.Context;

import com.sanus.sanus.domain.comments.view.CommentsView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CommentsPresenter extends CommentsView {
    void viewComents(String idDoc);
    void showImage(String idImage, final Context context, final CircleImageView image);
}
