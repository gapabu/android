package com.sanus.sanus.domain.new_chat.view;


import com.sanus.sanus.domain.new_chat.data.Messages;

import java.util.List;

public interface NewChatView {
    void showDataDoctor();
    void setDataAdapter(List<Messages> commentsDoctorList);
    void goMessages();
    void goCurriculum();
    void goFramentChat();
    void viewMessagesByTipe();
    void sendMessagesByType();
    String getMessages();

    void enableButton();
    void disableButton();
}
