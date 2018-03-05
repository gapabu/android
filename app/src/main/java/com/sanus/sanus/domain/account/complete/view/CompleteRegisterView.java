package com.sanus.sanus.domain.account.complete.view;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public interface CompleteRegisterView {


    void selectMale();
    void selectFemale();

    String getName();
    String getLastName();
    String getEdadPosition();

    void showMessage(int msg);
    void goMain();

}
