package com.sanus.sanus.domain.new_chat.interactor;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.new_chat.data.Messages;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewChatInteractorImpl implements NewChatInteractor{
    private NewChatPresenter presenter;
    private final String TAG = this.getClass().getSimpleName();

    private List<Messages> commentsDoctorList = new ArrayList<>();

    public NewChatInteractorImpl(NewChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendMessages(final String idUser, final String idDoct, String fecha, String hora, String message) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("autor", idUser );
        messageMap.put("doctor", idDoct);
        messageMap.put("fecha", fecha);
        messageMap.put("hora", hora);
        messageMap.put("mensaje", message);
        messageMap.put("usuario", idUser);

        mFirestore.collection("mensajes").add(messageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "enviado con exito");
                presenter.getTipoUser(idUser,idDoct);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "fallo al enviar");
            }
        });
    }

    @Override
    public void viewMessages(String idDoc, String idUser) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("mensajes").whereEqualTo("doctor", idDoc).whereEqualTo("usuario", idUser).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        String mensaje = document.getString("mensaje");
                        commentsDoctorList.add(new Messages(mensaje));
                        presenter.setDataAdapter(commentsDoctorList);
                    }
                }else {
                    Log.d(TAG, "Data doen't exist");
                }
            }
        });

    }

    @Override
    public void getTipoUser(final String idUser, final String idDoct) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String userIdNow = user.getUid();
            DocumentReference usuarios = db.collection("usuarios").document(userIdNow);
            usuarios.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String tipo = document.getString("tipo");
                        if (tipo.equals("Paciente")) {
                            presenter.insertContact(userIdNow, idDoct);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
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
                presenter.goMessages();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        /*mFirestore.collection("contactos").document(idDoct).set(contactMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                presenter.goMessages();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/
    }



}
