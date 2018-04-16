package com.sanus.sanus.domain.comments.presenter;

import android.net.Uri;
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
    public void setDataAdapter(List<CommentsDoctor> commentsDoctorList) {
        view.setDataAdapter(commentsDoctorList);
    }


    @Override
    public void updatingCalification(String qualification) {
        interactor.updatingCalification(qualification);
    }

    @Override
    public String getComment() {
        return view.getComment();
    }

    @Override
    public String getCalificacion() {
        return view.getCalificacion();
    }

    @Override
    public String getIdDoctor() {
        return view.getIdDoctor();
    }

    @Override
    public void showPhoto(Uri photo, CircleImageView avatar) {
        view.showPhoto(photo, avatar);
    }

    @Override
    public void goCurriculum() {
        view.goCurriculum();
    }

    @Override
    public void goMainDoctor() {
        view.goMainDoctor();
    }

    @Override
    public void viewSendComment() {
        view.viewSendComment();
    }

    @Override
    public void viewComents(String idDoc) {
        interactor.viewComents(idDoc);
    }

    @Override
    public void showImage(String idImage,CircleImageView image) {
        interactor.showImage(idImage, image);
    }

    @Override
    public void onClickSaveData() {
        interactor.onClickSaveData();
    }

    @Override
    public void getDate() {
        interactor.getDate();
    }

}
