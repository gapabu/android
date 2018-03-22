package com.sanus.sanus.domain.new_chat.interactor;

public interface NewChatInteractor {
    void sendMessages(String idUser,String idDoct, String fecha, String hora, String message);
    void viewMessages(String idDoc, String idUser);
    void getTipoUser(final String idUser, final String idDoct);
    void insertContact(String idUser, String idDoct);
}
