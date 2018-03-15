package com.sanus.sanus.domain.select_doctor.interactor;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
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
    private final String TAG = this.getClass().getSimpleName();
    private SelectDoctorPresenter presenter;

    private List<SelectDoctor> commentsDoctorList = new ArrayList<>();
    private List<SelectDoctor> listAuxiliar = new ArrayList<>();

    public SelectDoctorInteractorImpl(SelectDoctorPresenter presenter){this.presenter = presenter;}

    @Override
    public void viewComents() {
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("doctores").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(final QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for(final DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        String user_id = doc.getDocument().getId();
                        Log.d(TAG, "id:" + user_id);
                        final String especialidad = doc.getDocument().getString("especialidad");


                        mFirestore.collection("usuarios").document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                String nombre = documentSnapshot.getString("nombre");
                                String apellido = documentSnapshot.getString("apellido");
                                String image = documentSnapshot.getString("avatar");

                                String usuario = nombre + " " +apellido;
                                Log.d(TAG, image);
                                commentsDoctorList.add(new SelectDoctor(usuario, especialidad));
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
                listAuxiliar.add(new SelectDoctor(commentsDoctorList.get(i).getNombre(),commentsDoctorList.get(i).getEspecialidad()));
            }
        }
        presenter.setDataAdapter(listAuxiliar);
    }
}