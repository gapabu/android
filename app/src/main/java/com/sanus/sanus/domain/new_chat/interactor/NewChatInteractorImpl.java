package com.sanus.sanus.domain.new_chat.interactor;


import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;

public class NewChatInteractorImpl implements NewChatInteractor{
    private NewChatPresenter presenter;

    public NewChatInteractorImpl(NewChatPresenter presenter) {
        this.presenter = presenter;
    }
}
