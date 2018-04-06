package com.sanus.sanus.domain.select_hour.interactor;


import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

public class SelectHourInteractorImpl implements SelectHourInteractor {
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();

    public SelectHourInteractorImpl(SelectHourPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewSchedules(String id) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("horarios").document(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d(TAG, "Current data: " + documentSnapshot.getData());
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });

    }
}
