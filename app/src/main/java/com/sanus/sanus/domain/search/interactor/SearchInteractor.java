package com.sanus.sanus.domain.search.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SearchInteractor {

    void init();
    void showImage(String idImage, Context context, CircleImageView image);
    void buscador(String texto);

}
