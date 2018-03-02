package com.sanus.sanus.domain.chat.interactor;

import com.sanus.sanus.domain.chat.presenter.ChatPresenter;

public class ChatInteractorImpl implements ChatInteractor {
    private ChatPresenter presenter;

    public ChatInteractorImpl(ChatPresenter presenter) {
        this.presenter = presenter;
    }
}
