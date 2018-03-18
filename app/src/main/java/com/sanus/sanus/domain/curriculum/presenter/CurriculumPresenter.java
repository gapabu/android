package com.sanus.sanus.domain.curriculum.presenter;

import android.content.Context;

import com.sanus.sanus.domain.curriculum.view.CurriculumView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CurriculumPresenter extends CurriculumView {
    void showImage(String idImage, final Context context, final CircleImageView image);

}
