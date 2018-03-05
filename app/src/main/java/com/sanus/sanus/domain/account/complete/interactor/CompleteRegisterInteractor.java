package com.sanus.sanus.domain.account.complete.interactor;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.storage.UploadTask;

public interface CompleteRegisterInteractor {

    void onActivityResult(int requestCode, int resultCode, Intent data, Context context);
    void onClickGenderMale();
    void onClickGenderFemale();

    void onClickSaveData();

    void BringImagePicker();

}
