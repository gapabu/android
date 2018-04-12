package com.sanus.sanus.domain.select_hour.interactor;


import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

import java.util.ArrayList;
import java.util.List;

public class SelectHourInteractorImpl implements SelectHourInteractor {
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

    public SelectHourInteractorImpl(SelectHourPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewSchedules(String idDoctor, String dia) {
        mFirestore.collection("horarios").whereEqualTo("doctor", idDoctor).whereEqualTo("dia", dia)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        List<String> horarios = new ArrayList<>();
                        for (DocumentSnapshot doc : value) {
                            String dataMensage = String.valueOf(doc.getData());
                            horarios.add(dataMensage);
                            Log.d(TAG, " " + horarios);

                        }
                        Log.d(TAG, "Data: " + horarios);
                    }
                });

    }
}
