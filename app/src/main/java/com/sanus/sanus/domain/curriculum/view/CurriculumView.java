package com.sanus.sanus.domain.curriculum.view;

import android.net.Uri;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CurriculumView {
    void goComments();
    void goNewChat();
    void showDataDoctor();
    void showPhoto(Uri uri);
}
