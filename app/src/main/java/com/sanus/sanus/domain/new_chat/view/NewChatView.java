package com.sanus.sanus.domain.new_chat.view;


import com.sanus.sanus.domain.new_chat.data.Messages;

import java.util.List;

public interface NewChatView {
    void showDataDoctor();
    void getDate();
    void setDataAdapter(List<Messages> commentsDoctorList);
    void goMessages();
    void goCurriculum();
    void goFramentChat();
    void viewMessagesByTipe();
}
