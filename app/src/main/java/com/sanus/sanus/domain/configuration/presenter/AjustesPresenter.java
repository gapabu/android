package com.sanus.sanus.domain.configuration.presenter;

import com.sanus.sanus.domain.configuration.view.AjustesView;

public interface AjustesPresenter extends AjustesView {

    void onResume();
    void logout();
    void onClickActive();
    void onClickInactive();
}
