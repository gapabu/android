package com.sanus.sanus.domain.citas.interactor;

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
import com.sanus.sanus.domain.citas.data.Citas;
import com.sanus.sanus.domain.citas.presenter.CitasPresenter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CitasInteractorImpl implements CitasInteractor {

    private final String TAG= this.getClass().getSimpleName();
    private CitasPresenter presenter;
    private List<Citas> busquedaDoctors = new ArrayList<>();
    private String userIdNow;
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
                cities.clear();
                busquedaDoctors.clear();
                for (DocumentSnapshot doc : value) {
                    String dataMensage = String.valueOf(doc.getData());
                    cities.add(dataMensage);
                    Log.d(TAG, "cite: " + cities);
                    final String hospital = doc.getString("hospital");
                    final String fecha = doc.getString("fecha");
                    final String hora = doc.getString("hora");

                    mFirestore.collection("usuarios").document(doc.getString("doctor")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                Log.d(TAG, "Current data: " + documentSnapshot.getData());

                                final String doctor = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));
                                final String avatar = documentSnapshot.getString("avatar");

                                mFirestore.collection("hospitales").document(hospital).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                        if (documentSnapshot != null && documentSnapshot.exists()){
                                            Log.d(TAG, "Current data: " + documentSnapshot.getData());
                                            String nameHospital = documentSnapshot.getString("nombre");
                                            busquedaDoctors.add(new Citas(doctor,nameHospital, fecha, hora, avatar));
                                            presenter.setDataAdapter(busquedaDoctors);
                                        }
                                    }
                                });

                            }
                        }
                    });
                }
                Log.d(TAG, "Data: " + e);
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
