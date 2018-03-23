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
                //presenter.viewMessages(idDoct, idUser);
                presenter.goMessages();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "fallo al enviar");
            }
        });
    }

    @Override
    public void viewMessages(final String idDoc, final String idUser) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        final FirebaseFirestore mMessage = FirebaseFirestore.getInstance();

        mFirestore.collection("mensajes").whereEqualTo("doctor", idDoc).whereEqualTo("usuario", idUser).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {


                        mMessage.collection("mensajes").whereEqualTo("doctor", idDoc).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (DocumentSnapshot doc : task.getResult()){
                                    String mensaje = doc.getString("mensaje");
                                    commentsDoctorList.add(new Messages(mensaje));
                                    presenter.setDataAdapter(commentsDoctorList);
                                }
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
