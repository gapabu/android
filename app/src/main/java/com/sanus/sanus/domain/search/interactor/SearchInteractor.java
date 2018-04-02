package com.sanus.sanus.domain.search.interactor;

import de.hdodenhof.circleimageview.CircleImageView;

public interface SearchInteractor {

    void init();
    void showImage(String idImage, CircleImageView image);
    void buscador(String texto);

}
