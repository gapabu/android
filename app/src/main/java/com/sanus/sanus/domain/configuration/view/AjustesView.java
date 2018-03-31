package com.sanus.sanus.domain.configuration.view;

import android.net.Uri;

public interface AjustesView {

    void goSplash();

    void showName(String name);
    void showData(String name, String email);
    void showPhoto(Uri photo);
    void selectActive();
    void selectInactive();
}
