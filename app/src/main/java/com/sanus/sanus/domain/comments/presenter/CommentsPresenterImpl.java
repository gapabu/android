package com.sanus.sanus.domain.comments.presenter;

import android.content.Context;

import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.interactor.CommentsInteractor;
import com.sanus.sanus.domain.comments.interactor.CommentsInteractorImpl;
import com.sanus.sanus.domain.comments.view.CommentsView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsPresenterImpl implements  CommentsPresenter{
    private CommentsView view;
    private CommentsInteractor interactor;

    public CommentsPresenterImpl(CommentsView view){
        this.view = view;
        interactor = new CommentsInteractorImpl(this);
    }


    @Override
    public void sendComments() {
        view.sendComments();

    }

    @Override
    public void setDataAdapter(List<CommentsDoctor> commentsDoctorList) {
        view.setDataAdapter(commentsDoctorList);
    }

    @Override
    public void viewComents(String idDoc) {
        interactor.viewComents(idDoc);
    }

    @Override
    public void showImage(String idImage, Context context, CircleImageView image) {
        interactor.showImage(idImage, context, image);
    }
}
