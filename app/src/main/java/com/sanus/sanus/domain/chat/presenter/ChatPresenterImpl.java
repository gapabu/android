package com.sanus.sanus.domain.chat.presenter;

import com.sanus.sanus.domain.chat.interactor.ChatInteractor;
import com.sanus.sanus.domain.chat.interactor.ChatInteractorImpl;
import com.sanus.sanus.domain.chat.view.ChatFragmentView;

public class ChatPresenterImpl implements ChatPresenter {
    private ChatFragmentView view;
    private ChatInteractor interactor;

    public ChatPresenterImpl(ChatFragmentView view) {
        this.view = view;
        interactor = new ChatInteractorImpl(this);
    }
}
