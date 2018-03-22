package com.sanus.sanus.domain.chat.presenter;

import android.content.Context;

import com.sanus.sanus.domain.chat.view.ChatFragmentView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ChatPresenter extends ChatFragmentView{
    void init();
    void showImage(String idImage, final Context context, final CircleImageView image);
}
