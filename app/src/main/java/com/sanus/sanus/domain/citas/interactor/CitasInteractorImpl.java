package com.sanus.sanus.domain.citas.interactor;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.domain.citas.presenter.CitasPresenter;

import java.util.ArrayList;
import java.util.List;


public class CitasInteractorImpl implements CitasInteractor {

    private final String TAG= this.getClass().getSimpleName();

    private CitasPresenter presenter;
    private List<Citas> busquedaDoctors = new ArrayList<>();


    public CitasInteractorImpl(CitasPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("citas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        String doctor = doc.getDocument().getString("doctor");
                        String hospital = doc.getDocument().getString("hospital");
                        String fecha = doc.getDocument().getString("fecha");
                        String hora = doc.getDocument().getString("hora");
                        //String usuario = doc.getDocument().getString("usuario");

                        busquedaDoctors.add(new Citas(doctor,hospital, fecha, hora));

                        presenter.setDataAdapter(busquedaDoctors);

                    }
                }
            }
        });

    }
}
