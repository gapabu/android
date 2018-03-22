package com.sanus.sanus.domain.doctor_module.all_comments.presenter;

import android.content.Context;

import com.sanus.sanus.domain.doctor_module.all_comments.data.AllCommentsDoctor;
import com.sanus.sanus.domain.doctor_module.all_comments.interactor.AllCommentsInteractor;
import com.sanus.sanus.domain.doctor_module.all_comments.interactor.AllCommentsInteractorImpl;
import com.sanus.sanus.domain.doctor_module.all_comments.view.AllCommetnsView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCommetnsPresenterImpl implements  AllCommentsPresenter{
    private AllCommetnsView view;
    private AllCommentsInteractor interactor;

    public AllCommetnsPresenterImpl(AllCommetnsView view){
        this.view = view;
        interactor = new AllCommentsInteractorImpl(this);
    }


    @Override
    public void viewComents(String idDoc) {
        interactor.viewComents(idDoc);
    }

    @Override
    public void showImage(String idImage, Context context, CircleImageView image) {
        interactor.showImage(idImage, context, image);
    }

    @Override
    public void setDataAdapter(List<AllCommentsDoctor> commentsDoctorList) {
        view.setDataAdapter(commentsDoctorList);
    }
}
