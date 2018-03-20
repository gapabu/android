package com.sanus.sanus.domain.curriculum.interactor;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.utils.glide.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurriculumInteractorImpl implements CurriculumInteractor{
    private CurriculumPresenter presenter;
    private String TAG = getClass().getSimpleName();


    public CurriculumInteractorImpl(CurriculumPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showImage(String idImage, final Context context, final CircleImageView image) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                GlideApp.with(context).load(uri.toString()).placeholder(R.drawable.user).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d(TAG, "Sin conexion");
            }
        });
    }
}
