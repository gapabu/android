package com.sanus.sanus.domain.select_day.interactor;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.data.repository.firebase.entity.user.AppointmentEntity;
import com.sanus.sanus.domain.select_day.presenter.SelectDayPresenter;

public class SelectDayInteractorImpl implements SelectDayInteractor{
    private SelectDayPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private AppointmentEntity appointmentEntity = new AppointmentEntity();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private String userIdNow;
    private String idDocument;

    public SelectDayInteractorImpl(SelectDayPresenter presenter){this.presenter = presenter;}

    @Override
    public void addAppointment(String idHospital, final String idDoctor, String fecha) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {userIdNow = user.getUid();}
        appointmentEntity.hospital = idHospital;
        appointmentEntity.doctor = idDoctor;
        appointmentEntity.fecha = fecha;
        appointmentEntity.usuario = userIdNow;
        mFirestore.collection("citas").add(appointmentEntity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        idDocument = documentReference.getId();
                        presenter.next(idDocument);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    @Override
    public void deleteAppointment(String idDocument) {
       mFirestore.collection("citas").document(idDocument).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                presenter.previous();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error deleting document", e);
            }
        });
    }
}
