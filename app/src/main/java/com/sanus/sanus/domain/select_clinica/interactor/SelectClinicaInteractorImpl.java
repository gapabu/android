package com.sanus.sanus.domain.select_clinica.interactor;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.select_clinica.data.SelectClinica;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mireya on 10/03/2018.
 */

public class SelectClinicaInteractorImpl implements SelectClinicaInteractor {
    private final String TAG = this.getClass().getSimpleName();
    private SelectClinicaPresenter presenter;
    private List<SelectClinica> selectClinicaList = new ArrayList<>();

    public SelectClinicaInteractorImpl(SelectClinicaPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewClinica() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("hospitales").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        String nombre = doc.getDocument().getString("nombre");
                        String direccion = doc.getDocument().getString("direccion");
                        selectClinicaList.add(new SelectClinica(nombre, direccion));
                        presenter.setDataAdapter(selectClinicaList);
                    }
                    }
            }
        });

    }
}
