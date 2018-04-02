package com.sanus.sanus.domain.comments.view;

import android.net.Uri;

import com.sanus.sanus.domain.comments.data.CommentsDoctor;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CommentsView {

    void setDataAdapter(List<CommentsDoctor> commentsDoctorList);
    String getComment();
    String getCalificacion();
    String getIdDoctor();
    void showPhoto(Uri photo, CircleImageView avatar);
}
