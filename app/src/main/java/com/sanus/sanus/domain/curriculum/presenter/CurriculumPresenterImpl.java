package com.sanus.sanus.domain.curriculum.presenter;

import android.net.Uri;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sanus.sanus.domain.curriculum.interactor.CurriculumInteractor;
import com.sanus.sanus.domain.curriculum.interactor.CurriculumInteractorImpl;
import com.sanus.sanus.domain.curriculum.view.CurriculumView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurriculumPresenterImpl implements CurriculumPresenter {
    private CurriculumView view;
    private CurriculumInteractor interactor;

    public CurriculumPresenterImpl(CurriculumView view) {
        this.view = view;
        interactor = new CurriculumInteractorImpl(this);
    }

    @Override
    public void init(String idDoct, RatingBar ratingBar, TextView especialidad, TextView cedula, TextView cv) {
        interactor.init(idDoct,ratingBar,especialidad,cedula,cv);
    }

    @Override
    public void showImage(String idImage) {
        interactor.showImage(idImage);
    }

    @Override
    public void insertContact(String idUser, String idDoct) {
        interactor.insertContact(idUser,idDoct);
    }

    @Override
    public void verifyContact(String idUser, String idDoct) {
        interactor.verifyContact(idUser, idDoct);
    }


    @Override
    public void goComments() {
        view.goComments();
    }

    @Override
    public void goNewChat() {
        view.goNewChat();
    }

    @Override
    public void showDataDoctor() {
        view.showDataDoctor();
    }

    @Override
    public void showPhoto(Uri uri) {
        view.showPhoto(uri);
    }
}
