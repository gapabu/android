package com.sanus.sanus.domain.new_chat.presenter;


import com.sanus.sanus.domain.new_chat.view.NewChatView;

public interface NewChatPresenter extends NewChatView{
    void sendMessages(String idUser,String idDoct, String fecha, String hora, String message);
    void getDate();
}
