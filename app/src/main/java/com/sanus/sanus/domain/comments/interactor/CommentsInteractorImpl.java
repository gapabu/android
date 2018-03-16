package com.sanus.sanus.domain.comments.interactor;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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
    public void viewComents() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("comentarios").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for (final DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        String usuario1 = doc.getDocument().getString("usuario");
                        final String fecha = doc.getDocument().getString("fecha");
                        final String comentario = doc.getDocument().getString("comentario");
                        final String calificacion1 = doc.getDocument().getString("calificacion");

                        mFirestore.collection("usuarios").document(usuario1).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                final String image = documentSnapshot.getString("avatar");
                                String usuario = nombre + " " +apellido;
                                commentsDoctorList.add(new CommentsDoctor(usuario,comentario, fecha, calificacion1,image));
                                presenter.setDataAdapter(commentsDoctorList);
                            }
                        });
                    }else {
                        Log.d(TAG, "Data doen't exist");
                    }
                }
            }
        });
    }


}
