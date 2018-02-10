package com.sanus.sanus.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.Adapters.BusquedaDoctorAdapter;
import com.sanus.sanus.Data.BusquedaDoctor;
import com.sanus.sanus.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Mireya on 08/02/2018.
 */

public class BusquedaFragment extends Fragment {
    RecyclerView recyclerView;
    List<BusquedaDoctor> busquedaDoctors;
    BusquedaDoctorAdapter adapter;
    private FirebaseFirestore mFirestore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_buscar, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        mFirestore = FirebaseFirestore.getInstance();

        initializedData();
        adapter = new BusquedaDoctorAdapter(getActivity().getApplicationContext(), busquedaDoctors);
        recyclerView.setAdapter(adapter);
    }

    private void initializedData() {
        busquedaDoctors = new ArrayList<>();

       mFirestore.collection("doctores").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
               if (e!=null){
                   Log.d(TAG, "Error: " + e.getMessage());
               }
               for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                   if(doc.getType() == DocumentChange.Type.ADDED){
                       String nombre = doc.getDocument().getString("nombre");
                       String especialidad = doc.getDocument().getString("especialidad");

                       busquedaDoctors.add(new BusquedaDoctor(nombre, especialidad));

                       adapter.notifyDataSetChanged();
                   }
               }
           }
       });
    }
}

