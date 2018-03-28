package com.sanus.sanus.domain.configuration.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.sanus.sanus.domain.configuration.presenter.AjustesPresenter;


import java.util.HashMap;
import java.util.Map;

public class AjustesInteractorImpl implements AjustesInteractor{
    private AjustesPresenter presenter;
    private String idUser;


    public AjustesInteractorImpl(AjustesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        showAccount();
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();

        presenter.goSplash();
    }

    @Override
    public void onClickActive(final String activo) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            idUser = user.getUid();
        }

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        final FirebaseFirestore mFirestoreU = FirebaseFirestore.getInstance();
        mFirestore.collection("usuarios").document(idUser).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                String apellido = documentSnapshot.getString("apellido");
                String avatar = documentSnapshot.getString("avatar");
                String edad = documentSnapshot.getString("edad");
                String nombre = documentSnapshot.getString("nombre");
                String sexo = documentSnapshot.getString("sexo");
                String tipo = documentSnapshot.getString("tipo");

                Map<String, String> userMap = new HashMap<>();
                userMap.put("apellido", apellido);
                userMap.put("avatar", avatar);
                userMap.put("edad", edad);
                userMap.put("nombre", nombre);
                userMap.put("sexo", sexo);
                userMap.put("tipo", tipo);
                userMap.put("estado", activo);

                mFirestoreU.collection("usuarios").document(idUser).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Ajustes", "exitos" );
                    }
                });
            }
        });
    }


    private void showAccount(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            presenter.showData(user.getDisplayName(), user.getEmail());

            if (user.getPhotoUrl()!=null) {
                presenter.showPhoto(user.getPhotoUrl().toString());
            }
        }else{
            presenter.showData("Nombre", "Correo");
        }
    }
}
