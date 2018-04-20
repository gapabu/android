package com.sanus.sanus.domain.select_hour.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.data.repository.firebase.entity.user.AppointmentEntity;
import com.sanus.sanus.domain.select_hour.data.SelectHour;
import com.sanus.sanus.domain.select_hour.presenter.SelectHourPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHourInteractorImpl implements SelectHourInteractor {
    private SelectHourPresenter presenter;
    private String TAG = this.getClass().getSimpleName();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private List<SelectHour> busquedaDoctors =  new ArrayList<>();
    private AppointmentEntity appointmentEntity = new AppointmentEntity();
    private String userIdNow;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public SelectHourInteractorImpl(SelectHourPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewSchedules(final String idDoctor,final String dia) {
        mFirestore.collection("horarios").document(idDoctor).collection(dia) .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable final FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }
                List<String> shedules= new ArrayList<>();
                shedules.clear();
                busquedaDoctors.clear();
                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        String dataShedules = String.valueOf(dc.getDocument().getData());
                        shedules.add(dataShedules);
                        Log.d(TAG, "horarios: " + shedules);
                        final String itemHour = dc.getDocument().getString("hora");
                        mFirestore.collection("citas-ocupadas").document(idDoctor).collection("hora").addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                                if (e != null) {
                                    Log.w(TAG, "listen:error", e);
                                }else {
                                    if (snapshots.isEmpty()){
                                        Log.d(TAG, "no document");
                                        busquedaDoctors.add(new SelectHour(itemHour));
                                        presenter.setDataAdapter(busquedaDoctors);
                                    }else {
                                        List<String> shedulesOccupied = new ArrayList<>();
                                        shedulesOccupied.clear();
                                        busquedaDoctors.clear();
                                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                                String dataShedulesOccupied = String.valueOf(dc.getDocument().getData());
                                                shedulesOccupied.add(dataShedulesOccupied);
                                                Log.d(TAG, "citas-ocupadas: " + shedulesOccupied);
                                                String itemSchedules = dc.getDocument().getString("hora");
                                                if (!itemHour.equals(itemSchedules)){
                                                    busquedaDoctors.add(new SelectHour(itemHour));
                                                    presenter.setDataAdapter(busquedaDoctors);
                                                }else {
                                                    busquedaDoctors.clear();
                                                    presenter.setDataAdapter(busquedaDoctors);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void addAppointment(String idHospital, final String idDoctor, final String fecha, final String hora, String idDocument) {

        if (user != null) {userIdNow = user.getUid();}
        appointmentEntity.hospital = idHospital;
        appointmentEntity.doctor = idDoctor;
        appointmentEntity.fecha = fecha;
        appointmentEntity.hora = hora;
        appointmentEntity.usuario = userIdNow;

        mFirestore.collection("citas").document(idDocument).set(appointmentEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "agregando en coleccion cita");
                addDataCite(idDoctor, fecha, hora);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });
    }

    @Override
    public void deleteAppointment(String idDocument) {
        mFirestore.collection("citas").document(idDocument).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Borrando exitosamente en citas!");
                presenter.previous();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error deleting document", e);
            }
        });
    }

    @Override
    public void addDataCite(final String idDoctor, String fecha, String hora) {
        Map<String, Object> dataFecha = new HashMap<>();
        dataFecha.put("fecha", fecha);
        final Map<String, Object> dataHora = new HashMap<>();
        dataHora.put("hora", hora);

        mFirestore.collection("citas-ocupadas").document(idDoctor).collection("fecha").add(dataFecha).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Agregando en coleccion citas-ocupadas IDFecha: " + documentReference.getId());
                mFirestore.collection("citas-ocupadas").document(idDoctor).collection("hora").add(dataHora).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Agregando en coleccion citas-ocupadas IDHora: " + documentReference.getId());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }
}
