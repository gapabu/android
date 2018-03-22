package com.sanus.sanus.domain.doctor_module.chat.presenter;

import android.content.Context;

import com.sanus.sanus.domain.doctor_module.chat.data.ContactDoctor;
import com.sanus.sanus.domain.doctor_module.chat.interactor.ChatDoctorInteractor;
import com.sanus.sanus.domain.doctor_module.chat.interactor.ChatDoctorInteractorImpl;
import com.sanus.sanus.domain.doctor_module.chat.view.ChatDoctorFragmentView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatDoctorPresentImpl implements ChatDoctorPresent{
    private ChatDoctorFragmentView view;
    private ChatDoctorInteractor interactor;

    public ChatDoctorPresentImpl(ChatDoctorFragmentView view){
        this.view = view;
        interactor = new ChatDoctorInteractorImpl(this);
    }

    @Override
    public void init() {
        interactor.init();
    }


    @Override
    public void showImage(String idImage, Context context, CircleImageView image) {
        interactor.showImage(idImage,context,image);
    }


    @Override
    public void setDataAdapter(List<ContactDoctor> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }
}
