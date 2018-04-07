package com.sanus.sanus.domain.citas.interactor;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
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
    private String userIdNow, doctor, hospital, fecha, hora;
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();


    public CitasInteractorImpl(CitasPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {userIdNow = user.getUid();}

        mFirestore.collection("citas").whereEqualTo("usuario", userIdNow).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {Log.w(TAG, "Listen failed.", e);return;}

                List<String> cities = new ArrayList<>();
                for (DocumentSnapshot doc : value) {
                    String dataMensage = String.valueOf(doc.getData());
                    cities.add(dataMensage);
                    Log.d(TAG, "cite: " + cities);
                    hospital = doc.getString("hospital");
                    fecha = doc.getString("fecha");
                    hora = doc.getString("hora");

                    mFirestore.collection("usuarios").document(doc.getString("doctor")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                Log.d(TAG, "Current data: " + documentSnapshot.getData());
                                doctor = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                            }
                            mFirestore.collection("hospitales").document(hospital).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                    if (documentSnapshot != null && documentSnapshot.exists()){
                                        Log.d(TAG, "Current data: " + documentSnapshot.getData());
                                        String nameHospital = documentSnapshot.getString("nombre");
                                        busquedaDoctors.add(new Citas(doctor,nameHospital, fecha, hora));
                                        presenter.setDataAdapter(busquedaDoctors);
                                    }
                                }
                            });
                        }
                    });
                }
                Log.d(TAG, "Data: " + e);
            }
        });

    }
}
