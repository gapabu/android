package com.sanus.sanus.domain.curriculum.interactor;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.curriculum.presenter.CurriculumPresenter;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurriculumInteractorImpl implements CurriculumInteractor{
    private CurriculumPresenter presenter;
    private String TAG = getClass().getSimpleName();


    public CurriculumInteractorImpl(CurriculumPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init(String idDoct, final RatingBar ratingBar, final TextView especialidad, final TextView cedula, final TextView cv) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").document(idDoct).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String especialidad1 = task.getResult().getString("especialidad");
                        String cedul = task.getResult().getString("cedula");
                        String cv1 = task.getResult().getString("cv");
                        String comentarios = task.getResult().getString("comentario");
                        String calificacion = task.getResult().getString("calificacion");
                        Integer comen = Integer.parseInt(comentarios);
                        Integer cal = Integer.parseInt(calificacion);

                        if (comen == 0 && cal == 0){
                            ratingBar.setRating(0);
                        }else {
                            Integer valoracion = (cal / comen) / 20;
                            ratingBar.setRating(valoracion);
                        }

                        especialidad.setText(especialidad1);
                        cedula.setText(cedul);
                        cv.setText(cv1);
                    }
                }
            }
        });
    }

    @Override
    public void showImage(String idImage, final CircleImageView image) {
        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sanus-27.appspot.com/avatar/");
        storageReference.child(idImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                presenter.showPhoto(uri, image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Log.d(TAG, "Sin conexion");
            }
        });
    }

    @Override
    public void insertContact(String idUser, String idDoct) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        Map<String, String> contactMap = new HashMap<>();
        contactMap.put("autor", idUser );
        contactMap.put("doctor", idDoct);


        mFirestore.collection("contactos").add(contactMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "correcto");
                presenter.goNewChat();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "error al insertar");

            }
        });

    }

    @Override
    public void verifyContact(final String idUser, final String idDoct) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("contactos").whereEqualTo("autor", idUser).whereEqualTo("doctor", idDoct).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        presenter.goNewChat();
                    }
                }
                if (task.getResult().isEmpty()){
                    Log.d(TAG, "No have data");
                    presenter.insertContact(idUser, idDoct);
                }else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

}
