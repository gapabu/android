package com.sanus.sanus.domain.comments.interactor;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CommentsInteractor {
    void viewComents(String idDoc);
    void showImage(String idImage, CircleImageView image);
    void onClickSaveData();
    void getDate();
    void updatingCalification(String qualification);
}
