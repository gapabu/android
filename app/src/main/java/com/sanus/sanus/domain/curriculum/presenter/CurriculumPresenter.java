package com.sanus.sanus.domain.curriculum.presenter;

import android.content.Context;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sanus.sanus.domain.curriculum.view.CurriculumView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CurriculumPresenter extends CurriculumView {
    void init(String idDoct, final RatingBar ratingBar, final TextView especialidad, final TextView cedula, final TextView cv);
    void showImage(String idImage, final Context context, final CircleImageView image);
    void insertContact(String idUser, String idDoct);
    void verifyContact(final String idUser, final String idDoct);

}
