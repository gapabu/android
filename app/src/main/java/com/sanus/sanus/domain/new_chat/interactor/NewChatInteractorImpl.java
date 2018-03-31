package com.sanus.sanus.domain.new_chat.interactor;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
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
    private String userIdNow;

    private List<Messages> commentsDoctorList = new ArrayList<>();
    public NewChatInteractorImpl(NewChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendMessages(final String idUser, final String idDoct, String fecha, String hora, String message, String id) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("autor", idUser );
        messageMap.put("doctor", idDoct); //idUser
        messageMap.put("fecha", fecha);
        messageMap.put("hora", hora);
        messageMap.put("mensaje", message);
        messageMap.put("usuario", id); //idDoctor

        mFirestore.collection("mensajes").add(messageMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "enviado con exito");
                //presenter.goMessages();
                presenter.viewMessagesByTipe();
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

        mFirestore.collection("mensajes").whereEqualTo("doctor", idDoc).whereEqualTo("usuario", idUser)
              .orderBy("hora", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<String> mensages = new ArrayList<>();
                mensages.clear();
                commentsDoctorList.clear();

                for (DocumentSnapshot doc : value) {
                    String dataMensage = String.valueOf(doc.getData());
                    mensages.add(dataMensage);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {userIdNow = user.getUid();}

                    Log.d(TAG, "ms: " + mensages);
                    String mensaje = doc.getString("mensaje");
                    String autor = doc.getString("autor");
                    String hour = doc.getString("hora");
                    commentsDoctorList.add(new Messages(mensaje, autor, userIdNow, hour));
                    presenter.setDataAdapter(commentsDoctorList);
                }
                Log.d(TAG, "Current data:  " + mensages);
            }
        });

    }

}
