package com.sanus.sanus.domain.comments.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.comments.data.CommentsDoctor;
import com.sanus.sanus.domain.comments.presenter.CommentsPresenter;

import java.util.ArrayList;
import java.util.List;

public class CommentsInteractorImpl implements CommentsInteractor {
    private final String TAG = this.getClass().getSimpleName();
    private CommentsPresenter presenter;


    private List<CommentsDoctor> commentsDoctorList = new ArrayList<>();

    public CommentsInteractorImpl(CommentsPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewComents(String idDoc) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("comentarios").whereEqualTo("doctor", idDoc).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        String usuario1 = document.getString("usuario");
                        final String fecha = document.getString("fecha");
                        final String comentario = document.getString("comentario");
                        final String calificacion1 = document.getString("calificacion");

                        mFirestore.collection("usuarios").document(usuario1).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                final String image = documentSnapshot.getString("avatar");
                                String usuario = nombre + " " + apellido;
                                commentsDoctorList.add(new CommentsDoctor(usuario, comentario, fecha, calificacion1, image));
                                presenter.setDataAdapter(commentsDoctorList);
                            }
                        });

                    }
                }else {
                    Log.d(TAG, "Data doen't exist");
                }
            }
        });

    }

}
