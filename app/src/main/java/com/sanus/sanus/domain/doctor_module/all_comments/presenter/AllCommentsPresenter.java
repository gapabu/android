package com.sanus.sanus.domain.doctor_module.all_comments.presenter;

import android.content.Context;
import com.sanus.sanus.domain.doctor_module.all_comments.view.AllCommetnsView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface AllCommentsPresenter extends AllCommetnsView {
    void viewComents(String idDoc);
    void showImage(String idImage, final Context context, final CircleImageView image);
}
