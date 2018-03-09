package com.sanus.sanus.domain.select_clinica.interactor;


import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.select_clinica.data.ClinicaData;
import com.sanus.sanus.domain.select_clinica.presenter.SelectClinicaPresenter;

import java.util.ArrayList;
import java.util.List;

public class SelectClinicaInteractorImpl implements SelectClinicaInteractor{
    private SelectClinicaPresenter presenter;
    private final String TAG = this.getClass().getSimpleName();
    private List<ClinicaData> clinicaDataList = new ArrayList<>();
    private String nombre;
    private String direccion;

    public SelectClinicaInteractorImpl(SelectClinicaPresenter presenter){this.presenter = presenter;}

    @Override
    public void init() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("hospitales").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }

                for (DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        nombre = doc.getDocument().getString("nombre");
                        direccion = doc.getDocument().getString("direccion");
                        clinicaDataList.add(new ClinicaData(nombre, direccion));
                       presenter.setDataAdapter(clinicaDataList);
                    }
                }
            }
        });

    }
}
