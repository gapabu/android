package com.sanus.sanus.domain.curriculum.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CurriculumInteractor {
    void showImage(String idImage, final Context context, final CircleImageView image);
}

