package com.sanus.sanus.domain.select_doctor.interactor;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;
import java.util.ArrayList;
import java.util.List;

public class SelectDoctorInteractorImpl implements SelectDoctorInteractor {
    private final String TAG = this.getClass().getSimpleName();
    private SelectDoctorPresenter presenter;

    private List<SelectDoctor> commentsDoctorList = new ArrayList<>();
    private List<SelectDoctor> listAuxiliar = new ArrayList<>();

    public SelectDoctorInteractorImpl(SelectDoctorPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewComents(String idDoc) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("doctores").whereEqualTo("hospital", idDoc).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        final String user_id = document.getId();
                        final String especialidad = document.getString("especialidad");

                        mFirestore.collection("usuarios").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                final String image = documentSnapshot.getString("avatar");
                                String usuario = nombre + " " + apellido;
                                //Log.d(TAG, image);
                                commentsDoctorList.add(new SelectDoctor(usuario, especialidad, image, user_id));
                                presenter.setDataAdapter(commentsDoctorList);
                            }
                            });
                        }
                    }
                }
            });



    }

    @Override
    public void buscador(String texto) {
        listAuxiliar.clear();
        if(texto.isEmpty()){
            presenter.setDataAdapter(commentsDoctorList);
            return;
        }
        for (int i = 0; i < commentsDoctorList.size(); i++) {
            if(commentsDoctorList.get(i).getEspecialidad().toLowerCase().contains(texto.toLowerCase())){
                listAuxiliar.add(new SelectDoctor(commentsDoctorList.get(i).getNombre(),commentsDoctorList.get(i).getEspecialidad(),
                        commentsDoctorList.get(i).getAvatar(), commentsDoctorList.get(i).getId()));
            }
        }
        presenter.setDataAdapter(listAuxiliar);
    }
}