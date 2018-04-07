package com.sanus.sanus.domain.doctor_module.citas.interactor;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.doctor_module.citas.data.Appointment;
import com.sanus.sanus.domain.doctor_module.citas.presenter.CitasDoctorPresenter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

                            final String fecha = doc.getString("fecha");
                            final String hora = doc.getString("hora");

                            mFirestore.collection("usuarios").document(doc.getString("usuario")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                    if (e != null) {
                                        Log.w(TAG, "Listen failed.", e);
                                        return;
                                    }

                                    if (documentSnapshot != null && documentSnapshot.exists()) {
                                        Log.d(TAG, "Current data: " + documentSnapshot.getData());
                                        String usuario = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                                        String avatar = documentSnapshot.getString("avatar");
                                        commentsDoctorList.add(new Appointment(usuario, fecha, hora, avatar));
                                        presenter.setDataAdapter(commentsDoctorList);
                                    } else {
                                        Log.d(TAG, "Current data: null");
                                    }
                                }
                            });


                        }
                        Log.d(TAG, "Current cites in CA: " + mensages);
                    }
                });
    }

    @Override
    public void showImage(String idImage, final CircleImageView image) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                presenter.showPhoto(uri, image);
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
