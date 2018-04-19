package com.sanus.sanus.domain.resume_new_cita.interactor;

import android.net.Uri;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.data.repository.firebase.entity.user.AppointmentEntity;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;

public class ResumeNewCitaInteractorImpl implements ResumeNewCitaInteractor {
    private String TAG = this.getClass().getSimpleName();
    private ResumeNewCitaPresenter presenter;
    private AppointmentEntity appointmentEntity = new AppointmentEntity();
    private String userIdNow;
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public ResumeNewCitaInteractorImpl (ResumeNewCitaPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void addAppointment(String idHospital, String idDoctor, String fecha, String hora, String idDocument) {

        if (user != null) {userIdNow = user.getUid();}
        appointmentEntity.hospital = idHospital;
        appointmentEntity.doctor = idDoctor;
        appointmentEntity.fecha = fecha;
        appointmentEntity.hora = hora;
        appointmentEntity.usuario = userIdNow;


        mFirestore.collection("citas").document(idDocument).set(appointmentEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Escribiendo en citas exitosamente!");
                presenter.alertSuccessAppoitment();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error al escribir en citas", e);
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
                    Log.d(TAG, "Coleccion usuarios: " + documentSnapshot.getData());
                    String name = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                    String avatar = documentSnapshot.getString("avatar");

                    showImage(avatar);
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
                    Log.d(TAG, "Coleccion hospitales: " + documentSnapshot.getData());
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
                Log.d(TAG, "Borrando exitosamente en citas!");
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
    public void deleteAppointmentOccupied(final String idDoctor, final String idFecha, final String idHora) {
        mFirestore.collection("citas-ocupadas").document(idDoctor).collection("fecha").document(idFecha).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                mFirestore.collection("citas-ocupadas").document(idDoctor).collection("fecha").document(idHora).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void showImage(String idImage) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                presenter.showPhoto(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d(TAG, "No hay conexion");
            }
        });
    }

}
