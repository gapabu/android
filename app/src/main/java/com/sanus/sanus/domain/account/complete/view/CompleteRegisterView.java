package com.sanus.sanus.domain.account.complete.view;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public interface CompleteRegisterView {

    void setImageAvatar(Bitmap bitmap);
    void selectMale();
    void selectFemale();
    void showFileChooser(int requestCode);
    void fileStorageReference(StorageReference fileRef, Uri filePath);

    String getName();
    String getLastName();
    String getEdadPosition();

    void showMessage(int msg);
    void goMain();

    ProgressDialog getLoading();
}

