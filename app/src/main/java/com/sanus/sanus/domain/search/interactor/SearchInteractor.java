package com.sanus.sanus.domain.search.interactor;

import android.content.Context;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SearchInteractor {

    void init();
    void buscador(String texto);
    void showImage(String idImage, final Context context, final CircleImageView image);

}
