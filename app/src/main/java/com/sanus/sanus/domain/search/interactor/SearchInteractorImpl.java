package com.sanus.sanus.domain.search.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.search.data.BusquedaDoctor;
import com.sanus.sanus.domain.search.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchInteractorImpl implements SearchInteractor{
    private SearchPresenter presenter;
    private final String TAG= this.getClass().getSimpleName();
    private List<BusquedaDoctor> busquedaDoctors =  new ArrayList<>();
    private List<BusquedaDoctor> listAuxiliar = new ArrayList<>();
    private String nombre;
    private String especialidad;
    private String user_id;

    public SearchInteractorImpl(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void init() {

        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();


        mFirestore.collection("doctores").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(final QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        user_id = doc.getDocument().getId();
                        Log.d(TAG, "id: " + user_id + " " + especialidad);
                        //nombre = doc.getDocument().getString("nombre");
                        especialidad = doc.getDocument().getString("especialidad");



                        //busquedaDoctors.add(new BusquedaDoctor(nombre, especialidad).withId(user_id));

                        busquedaDoctors.add(new BusquedaDoctor(nombre, especialidad));
                        presenter.setDataAdapter(busquedaDoctors);

                    }
                }
            }
        });
    }

    @Override
    public void buscador(String texto) {

        listAuxiliar.clear();

        if(texto.isEmpty()){
            presenter.setDataAdapter(busquedaDoctors);
            return;
        }

        for (int i = 0; i < busquedaDoctors.size(); i++) {
            if(busquedaDoctors.get(i).getEspecialidad().toLowerCase().contains(texto.toLowerCase())){
                listAuxiliar.add(new BusquedaDoctor(busquedaDoctors.get(i).getNombre(),busquedaDoctors.get(i).getEspecialidad()));
            }
        }
        presenter.setDataAdapter(listAuxiliar);

    }
}
