package com.sanus.sanus.domain.new_chat.presenter;


import com.sanus.sanus.domain.new_chat.interactor.NewChatInteractor;
import com.sanus.sanus.domain.new_chat.interactor.NewChatInteractorImpl;
import com.sanus.sanus.domain.new_chat.view.NewChatView;

public class NewChatPresenterImpl implements NewChatPresenter{
    private NewChatView view;
    private NewChatInteractor interactor;

    public NewChatPresenterImpl(NewChatView view) {
        this.view = view;
        interactor = new NewChatInteractorImpl(this);
    }

    @Override
    public void sendMessages(String idUser, String idDoct, String fecha, String hora, String message) {
        interactor.sendMessages(idUser,idDoct,fecha,hora,message);
    }

    @Override
    public void showDataDoctor() {
        view.showDataDoctor();
    }

    @Override
    public void getDate() {
        view.getDate();
    }


}
