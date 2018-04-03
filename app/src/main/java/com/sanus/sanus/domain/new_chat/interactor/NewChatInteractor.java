package com.sanus.sanus.domain.new_chat.interactor;

public interface NewChatInteractor {
    void sendMessages(String idUser,String idDoct, String id);
    void viewMessages(String idDoc, String idUser);
    void getDate();
}
