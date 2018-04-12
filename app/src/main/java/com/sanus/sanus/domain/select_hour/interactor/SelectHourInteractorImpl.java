package com.sanus.sanus.domain.select_hour.interactor;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectHourInteractorImpl implements SelectHourInteractor {
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private List<SelectHour> commentsDoctorList = new ArrayList<>();

    public SelectHourInteractorImpl(SelectHourPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewSchedules(String idDoctor, String dia) {

        mFirestore.collection("horarios").document(idDoctor).collection(dia).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(final QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for (final DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        //Log.d(TAG, "New city: " + doc.getDocument().getData());
                        String hora = doc.getDocument().getString("hora");
                        Log.d(TAG, "New horario: " + hora);
                        commentsDoctorList.add(new SelectHour(hora));
                        presenter.setDataAdapter(commentsDoctorList);
                    }
                }
            }
        });
    }
}
