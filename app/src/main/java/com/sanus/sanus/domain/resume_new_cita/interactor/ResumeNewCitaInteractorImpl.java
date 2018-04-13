package com.sanus.sanus.domain.resume_new_cita.interactor;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.data.repository.firebase.entity.user.AppointmentEntity;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;
import com.sanus.sanus.utils.alert.CallbackAlert;

public class ResumeNewCitaInteractorImpl implements ResumeNewCitaInteractor, CallbackAlert {
    private String TAG = this.getClass().getSimpleName();
    private ResumeNewCitaPresenter presenter;
    private AppointmentEntity appointmentEntity = new AppointmentEntity();
    private String userIdNow;
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

    public ResumeNewCitaInteractorImpl (ResumeNewCitaPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void addAppointment(String idHospital, String idDoctor, String fecha, String hora, String idDocument) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {userIdNow = user.getUid();}
        appointmentEntity.hospital = idHospital;
        appointmentEntity.doctor = idDoctor;
        appointmentEntity.fecha = fecha;
        appointmentEntity.hora = hora;
        appointmentEntity.usuario = userIdNow;


        mFirestore.collection("citas").document(idDocument).set(appointmentEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
                presenter.showExitoRegistro();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });

    }

    @Override
    public void viewDataDoctor(final String idDoctor) {
        mFirestore.collection("usuarios").document(idDoctor).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d(TAG, "Current data: " + documentSnapshot.getData());
                    String name = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                    presenter.setNameDoctor(name);

                    mFirestore.collection("doctores").document(idDoctor).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                            if (documentSnapshot != null && documentSnapshot.exists()){
                                String specialty = documentSnapshot.getString("especialidad");
                                presenter.setSpecialty(specialty);
                            }
                        }
                    });

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    @Override
    public void viewDataHospital(final String idHospital) {
        mFirestore.collection("hospitales").document(idHospital).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d(TAG, "Current data: " + documentSnapshot.getData());
                    String name = documentSnapshot.getString("nombre");
                    String direction = documentSnapshot.getString("direccion");
                    presenter.setNameHospital(name);
                    presenter.setDirectionHospital(direction);
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    @Override
    public void deleteAppointment(String idDocument) {
        mFirestore.collection("citas").document(idDocument).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                presenter.goActivity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error deleting document", e);
            }
        });
    }

    @Override
    public void acceptAlert() {
        presenter.goActivity();
    }

    @Override
    public void cancelAlert() {
        presenter.goActivity();
    }
}
