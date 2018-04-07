package com.sanus.sanus.domain.citas.presenter;

import com.sanus.sanus.domain.citas.view.CitasView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface CitasPresenter extends CitasView{

    void init();
    void showImage(String idImage, CircleImageView image);
}
