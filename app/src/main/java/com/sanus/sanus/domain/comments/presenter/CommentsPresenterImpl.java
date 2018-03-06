package com.sanus.sanus.domain.comments.presenter;

import com.sanus.sanus.domain.comments.interactor.CommentsInteractor;
import com.sanus.sanus.domain.comments.interactor.CommentsInteractorImpl;
import com.sanus.sanus.domain.comments.view.CommentsView;

public class CommentsPresenterImpl implements  CommentsPresenter{
    private CommentsView view;
    private CommentsInteractor interactor;

    public CommentsPresenterImpl(CommentsView view){
        this.view = view;
        interactor = new CommentsInteractorImpl(this);
    }

}
