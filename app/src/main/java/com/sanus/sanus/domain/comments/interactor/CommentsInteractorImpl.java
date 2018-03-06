package com.sanus.sanus.domain.comments.interactor;

import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;

import java.util.List;

public class CommentsInteractorImpl implements CommentsInteractor {
    private CommentsPresenter presenter;

    private List<CommentsDoctor> commentsDoctorList;

    public CommentsInteractorImpl(CommentsPresenter presenter){this.presenter = presenter;}
}
