package com.sanus.sanus.domain.comments.view;

import com.sanus.sanus.domain.comments.data.CommentsDoctor;

import java.util.List;

public interface CommentsView {

    void sendComments();
    void setDataAdapter(List<CommentsDoctor> commentsDoctorList);
}
