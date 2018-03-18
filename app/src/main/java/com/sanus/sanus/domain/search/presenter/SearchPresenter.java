package com.sanus.sanus.domain.search.presenter;


import android.content.Context;

import com.sanus.sanus.domain.search.view.SearchView;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SearchPresenter extends SearchView {
    void init();
    void buscador(String texto);
    void showImage(String idImage, final Context context, final CircleImageView image);


}
