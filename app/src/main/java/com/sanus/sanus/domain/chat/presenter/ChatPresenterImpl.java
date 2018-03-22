package com.sanus.sanus.domain.chat.presenter;

import android.content.Context;

import com.sanus.sanus.domain.chat.data.ContactUser;
import com.sanus.sanus.domain.chat.interactor.ChatInteractor;
import com.sanus.sanus.domain.chat.interactor.ChatInteractorImpl;
import com.sanus.sanus.domain.chat.view.ChatFragmentView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatPresenterImpl implements ChatPresenter {
    private ChatFragmentView view;
    private ChatInteractor interactor;

    public ChatPresenterImpl(ChatFragmentView view) {
        this.view = view;
        interactor = new ChatInteractorImpl(this);
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
    public void setDataAdapter(List<ContactUser> busquedaDoctors) {
        view.setDataAdapter(busquedaDoctors);
    }
}
