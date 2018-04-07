package com.sanus.sanus.domain.doctor_module.citas.interactor;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;
import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenter;

import java.util.ArrayList;
import java.util.List;

public class CitasDoctorInteractorImpl implements CitasDoctorInteractor{
    private String TAG = this.getClass().getSimpleName();
    private CitasDoctorPresenter presenter;
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userIdNow;

    private List<Appointment> commentsDoctorList = new ArrayList<>();
    public CitasDoctorInteractorImpl(CitasDoctorPresenter presenter){this.presenter = presenter;}

    @Override
    public void init() {
        if (user != null) {userIdNow = user.getUid();}

        /*mFirestore.collection("citas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        Log.d(TAG, "New city: " + dc.getDocument().getData());
                    }
                }
            }
        });*/
        mFirestore.collection("citas").whereEqualTo("doctor", userIdNow).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        List<String> mensages = new ArrayList<>();
                        for (DocumentSnapshot doc : value) {
                            String dataMensage = String.valueOf(doc.getData());
                            mensages.add(dataMensage);
                            Log.d(TAG, "data: " + mensages);

                            String fecha = doc.getString("fecha");
                            String hora = doc.getString("hora");
                            String usuario = doc.getString("usuario");
                            commentsDoctorList.add(new Appointment(usuario, fecha, hora));
                            presenter.setDataAdapter(commentsDoctorList);

                        }
                        Log.d(TAG, "Current cites in CA: " + mensages);
                    }
                });
    }
}
