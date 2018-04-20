package com.sanus.sanus.domain.doctor_module.edit_curriculum.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.data.repository.firebase.entity.user.DoctorEntity;
import com.sanus.sanus.domain.doctor_module.edit_curriculum.presenter.EditCurriculumPresenter;

public class EditCurriculumInteractorImpl implements EditCurriculumInteractor {
    private EditCurriculumPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private DoctorEntity doctorEntity = new DoctorEntity();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private String idUser;

    public EditCurriculumInteractorImpl(EditCurriculumPresenter presenter){this.presenter = presenter;}

    @Override
    public void validateButtonEnable() {
        if (!presenter.getCurriculum().isEmpty()) {
            presenter.enableButton();
            return;
        }
        presenter.disableButton();
    }

    @Override
    public void saveCurriculum() {


        if (auth.getCurrentUser() != null) {
            idUser = auth.getCurrentUser().getUid();
        }

        mFirestore.collection("doctores").document(idUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }


                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    doctorEntity.cedula = snapshot.getString("cedula");
                    doctorEntity.especialidad = snapshot.getString("especialidad");
                    doctorEntity.hospital = snapshot.getString("hospital");
                    doctorEntity.calificacion = snapshot.getString("calificacion");
                    doctorEntity.comentario = snapshot.getString("comentario");
                    doctorEntity.cv = presenter.getCurriculum();

                    mFirestore.collection("doctores").document(idUser).set(doctorEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            presenter.alertSuccessUpdate();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });


                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    @Override
    public void getDataCurriculum() {
        if (auth.getCurrentUser() != null) {
            idUser = auth.getCurrentUser().getUid();
        }
        mFirestore.collection("doctores").document(idUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d(TAG, "Coleccion hospitales: " + documentSnapshot.getData());
                    String curriculum = documentSnapshot.getString("cv");
                    presenter.setCurriculum(curriculum);
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }
}
