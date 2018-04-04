package com.sanus.sanus.domain.new_chat.interactor;

import android.annotation.SuppressLint;
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
import com.sanus.sanus.data.repository.firebase.entity.user.MessageEntity;
import com.sanus.sanus.domain.new_chat.data.Messages;
import com.sanus.sanus.domain.new_chat.presenter.NewChatPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewChatInteractorImpl implements NewChatInteractor{
    private final String TAG = this.getClass().getSimpleName();
    private MessageEntity messageEntity = new MessageEntity();
    private NewChatPresenter presenter;
    private String userIdNow, hour, date;

    private List<Messages> commentsDoctorList = new ArrayList<>();
    public NewChatInteractorImpl(NewChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendMessages(String idUser, String idDoct, String id) {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        getDate();
        messageEntity.autor = idUser;
        messageEntity.doctor = idDoct;
        messageEntity.fecha = date;
        messageEntity.hora = hour;
        messageEntity.mensaje = presenter.getMessages();
        messageEntity.usuario = id;

        mFirestore.collection("mensajes").add(messageEntity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "enviado con exito");
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
    public void viewMessages(String idDoc, String idUser) {
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

                    String mensaje = doc.getString("mensaje");
                    String autor = doc.getString("autor");
                    commentsDoctorList.add(new Messages(mensaje, autor, userIdNow));
                    presenter.setDataAdapter(commentsDoctorList);
                }
                Log.d(TAG, "Data:  " + mensages);
            }
        });

    }

    @Override
    public void getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss:SS");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        hour = simpleTimeFormat.format(calendar.getTime());
        date = simpleDateFormat.format(calendar.getTime());
    }

}
