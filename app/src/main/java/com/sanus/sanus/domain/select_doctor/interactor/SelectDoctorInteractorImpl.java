package com.sanus.sanus.domain.select_doctor.interactor;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.select_doctor.data.SelectDoctor;
import com.sanus.sanus.domain.select_doctor.presenter.SelectDoctorPresenter;
import java.util.ArrayList;
import java.util.List;

public class SelectDoctorInteractorImpl implements SelectDoctorInteractor {
    private SelectDoctorPresenter presenter;

    private List<SelectDoctor> commentsDoctorList = new ArrayList<>();
    private List<SelectDoctor> listAuxiliar = new ArrayList<>();

    public SelectDoctorInteractorImpl(SelectDoctorPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewDoctor(String idHospital) {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("doctores").whereEqualTo("hospital", idHospital).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        final String user_id = document.getId();
                        final String especialidad = document.getString("especialidad");

                        mFirestore.collection("usuarios").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                final String image = documentSnapshot.getString("avatar");
                                String usuario = documentSnapshot.getString("nombre").concat(" " + documentSnapshot.getString("apellido"));

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