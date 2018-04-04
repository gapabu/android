package com.sanus.sanus.domain.new_chat.presenter;


import com.sanus.sanus.domain.new_chat.view.NewChatView;

public interface NewChatPresenter extends NewChatView{
    void viewMessages(String idDoc, String idUser);
    void sendMessages(String idUser,String idDoct,String id);

}
