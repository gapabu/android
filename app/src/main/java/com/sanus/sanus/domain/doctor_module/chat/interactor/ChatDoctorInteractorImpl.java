package com.sanus.sanus.domain.doctor_module.chat.interactor;

import com.sanus.sanus.domain.doctor_module.chat.presenter.ChatDoctorPresent;

public class ChatDoctorInteractorImpl implements ChatDoctorInteractor{
    private ChatDoctorPresent present;

    public ChatDoctorInteractorImpl(ChatDoctorPresent present){this.present = present;}
}
