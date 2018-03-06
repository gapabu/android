package com.sanus.sanus.domain.curriculum.interactor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.R;
import com.sanus.sanus.domain.comments.view.CommentsActivity;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;
import com.sanus.sanus.domain.curriculum.view.CurriculumActivity;

public class CurriculumInteractorImpl implements CurriculumInteractor {
    private CurriculumPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();

    public CurriculumInteractorImpl(CurriculumPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").document("MbisakX6endQjlgSdPRqDcAibpY2").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Log.d(TAG, "exist yes");
                        String nombre = task.getResult().getString("nombre");
                        String especialidad1 = task.getResult().getString("especialidad");
                        String cedul = task.getResult().getString("cedula");
                        String cv = task.getResult().getString("cv");
                        String image = task.getResult().getString("avatar");

                        Log.d(TAG, "data exist " + nombre + especialidad1);


                    }else{
                        Log.d(TAG, "no data: " );
                    }
                } else {
                    String error = task.getException().getMessage();
                    Log.d(TAG, "Error: " + error);

                }
            }
        });
    }

}
