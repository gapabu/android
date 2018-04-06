package com.sanus.sanus.domain.resume_new_cita.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sanus.sanus.data.repository.firebase.entity.user.AppointmentEntity;
import com.sanus.sanus.domain.resume_new_cita.presenter.ResumeNewCitaPresenter;

public class ResumeNewCitaInteractorImpl implements ResumeNewCitaInteractor{
    private String TAG = this.getClass().getSimpleName();
    private ResumeNewCitaPresenter presenter;
    private AppointmentEntity appointmentEntity = new AppointmentEntity();
    private String userIdNow;

    public ResumeNewCitaInteractorImpl (ResumeNewCitaPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void addAppointment(String idHospital, String idDoctor, String fecha, String hora) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {userIdNow = user.getUid();}
        appointmentEntity.hospital = idHospital;
        appointmentEntity.doctor = idDoctor;
        appointmentEntity.fecha = fecha;
        appointmentEntity.hora = hora;
        appointmentEntity.usuario = userIdNow;

        mFirestore.collection("citas").add(appointmentEntity).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "Se agrego con éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, " " + e);
            }
        });
    }
}
