package com.sanus.sanus.domain.doctor_module.chat.presenter;

import com.sanus.sanus.domain.doctor_module.chat.interactor.ChatDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.chat.interactor.ChatDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.chat.view.ChatDoctorFragmentView;

public class ChatDoctorPresentImpl implements ChatDoctorPresent{
    private ChatDoctorFragmentView view;
    private ChatDoctorInteractor interactor;

    public ChatDoctorPresentImpl(ChatDoctorFragmentView view){
        this.view = view;
        interactor = new ChatDoctorInteractorImpl(this);
    }
}
